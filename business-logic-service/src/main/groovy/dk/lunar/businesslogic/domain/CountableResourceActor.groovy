package dk.lunar.businesslogic.domain

import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.AbstractBehavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.javadsl.Receive
import dk.lunar.businesslogic.domain.commands.DecrementCommand
import dk.lunar.businesslogic.domain.commands.IncrementCommand
import groovy.util.logging.Log
import io.swagger.client.ApiException
import io.swagger.client.ApiResponse
import io.swagger.client.api.CountableResourceApi
import io.swagger.client.model.CountableResource

@Log
class CountableResourceActor extends AbstractBehavior<CountableResourceActorMsg> {

    static interface CountableResourceActorMsg {}

    static Behavior<CountableResourceActorMsg> create(String countableResourceName, CountableResourceApi countableResourceApi) {
        return Behaviors.setup(context -> new CountableResourceActor(context, countableResourceName, countableResourceApi))
    }

    private final CountableResourceApi countableResourceApi
    private String countableResourceName

    private CountableResource currentCountableResource
    private String currentETag

    private CountableResourceActor(ActorContext context, String countableResourceName, CountableResourceApi countableResourceApi) {
        super(context)
        this.countableResourceName = countableResourceName
        this.countableResourceApi = countableResourceApi
        this.currentCountableResource = null
        this.currentETag = null
    }

    @Override
    Receive<CountableResourceActorMsg> createReceive() {
        return newReceiveBuilder()
                .onMessage(IncrementCommand.class, this::onIncrement)
                .onMessage(DecrementCommand.class, this::onDecrement)
                .build()
    }

    private Behavior<CountableResourceActorMsg> onIncrement(IncrementCommand message) {
        try {

            if (!currentCountableResource) {
                createAndSetCountableResource()
            }

            currentCountableResource.count++
            updateCuntableResource()
            return this
        } catch (ApiException e) {
            context.log.error("failed - [httpStatus: ${e.code}, body: ${e.responseBody ?: e.message}]", e)
            return this
        }
    }

    private Behavior<CountableResourceActorMsg> onDecrement(DecrementCommand message) {
        try {
            if (!currentCountableResource) {
                createAndSetCountableResource()
            }

            currentCountableResource.count--
            updateCuntableResource()
            return this
        } catch (ApiException e) {
            context.log.error("failed - [httpStatus: ${e.code}, body: ${e.responseBody ?: e.message}]", e)
            return this
        }
    }

    private void updateCuntableResource() {
        try {
            //Den generede client indsætter currentETag i en If-Match header.
            ApiResponse<Void> response = countableResourceApi.updateCountableResourceWithHttpInfo(currentCountableResource, currentETag.toString(), countableResourceName)
            currentETag = response.getHeaders()['ETag'].first() - "\"" - "\""
        } catch (ApiException e) {
            if (e.code == 412) {
                context.log.error("Precondition failed - ETag not up to date - [httpStatus: ${e.code}, body: ${e.responseBody ?: e.message}, currentETag: ${currentETag}]")
                ApiResponse<CountableResource> response = countableResourceApi.getCountableResourceByNameWithHttpInfo(countableResourceName)
                currentCountableResource = response.data
                currentETag = response.getHeaders()['ETag'].first() - "\"" - "\""
                updateCuntableResource()
            } else {
                context.log.error("Failed creating resource [httpStatus: ${e.code}, body: ${e.responseBody}]")
                throw e
            }
        }

    }

    /**
     * Creeate resource if it doesnt exists, else get it.
     * @param name
     * @param count
     * @return ETag for the resource
     */
    private createAndSetCountableResource() {
        currentCountableResource = new CountableResource(name: countableResourceName, count: 1)
        context.log.info("Creating resource {} with count {}", currentCountableResource, 1)

        try {
            ApiResponse<Void> response = countableResourceApi.addCountableResourceWithHttpInfo(currentCountableResource)
            return response.getHeaders()['ETag'].first() - "\"" - "\""
        } catch (ApiException e) {
            if (e.code == 409) {
                context.log.error("Resource already exists - [httpStatus: ${e.code}, body: ${e.responseBody}]")
                getAndSetCountableResource()
            } else {
                context.log.error("Failed creating resource [httpStatus: ${e.code}, body: ${e.responseBody}]")
                throw e
            }
        }
    }

    private void getAndSetCountableResource() {
        ApiResponse<CountableResource> response = countableResourceApi.getCountableResourceByNameWithHttpInfo(countableResourceName)
        currentCountableResource = response.data
        currentETag = response.getHeaders()['ETag'].first() - "\"" - "\""
    }

}
