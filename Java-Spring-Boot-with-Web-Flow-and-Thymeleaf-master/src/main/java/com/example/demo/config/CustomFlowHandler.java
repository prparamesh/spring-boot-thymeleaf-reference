package com.example.demo.config;

import java.io.IOException;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.webflow.engine.RequestControlContext;
import org.springframework.webflow.engine.ViewState;
import org.springframework.webflow.engine.support.TransitionExecutingFlowExecutionExceptionHandler;
import org.springframework.webflow.execution.FlowExecutionException;

public class CustomFlowHandler extends
TransitionExecutingFlowExecutionExceptionHandler 
{
	

	public boolean canHandle(FlowExecutionException ex) {
//		if (findBusinessException(ex) != null) {
//			return true;
//		} else {
//			return false;
//		}
		
		return true;
		
	}

	public void handle(FlowExecutionException ex, RequestControlContext context) {
		context.getMessageContext().addMessage(
				new MessageBuilder().error().source(null).defaultText(
						ex.getMessage()).build());

		Object testState = context.getCurrentState();

		if (testState instanceof ViewState) {
			ViewState viewState = (ViewState) testState;
			try {
				viewState.getViewFactory().getView(context).render();
			} catch (IOException e) {
				// Properly handle rendering errors here
				
				System.err.println("Error :: "+ e);
			}
		}

	}

//	private MyBusinessException findBusinessException(FlowExecutionException ex) {
//		Throwable cause = ex.getCause();
//		while (cause != null) {
//			if (cause instanceof MyBusinessException) {
//				return (MyBusinessException) cause;
//			}
//			cause = cause.getCause();
//		}
//		return null;
//	}
	
	/*
	//private static final Logger LOGGER = LoggerFactory.getLogger(PerfilExceptionHandler.class);

	@Override
	public boolean canHandle(final FlowExecutionException exception) {
	    return exception.getCause() instanceof EvaluationException;
	}

	@Override
	public void handle(final FlowExecutionException exception, final RequestControlContext context) {

	    //LOGGER.info("handling exception {}", exception.getMessage());

	    TransitionExecutingFlowExecutionExceptionHandler handler = new TransitionExecutingFlowExecutionExceptionHandler();

	    handler.add(SnapshotNotFoundException.class, "success");
	}
	
	*/
}