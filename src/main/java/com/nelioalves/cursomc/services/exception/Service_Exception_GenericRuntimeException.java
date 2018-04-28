package com.nelioalves.cursomc.services.exception;

import java.lang.annotation.Annotation;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.annotations.NotFoundAction;

public class Service_Exception_GenericRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public Service_Exception_GenericRuntimeException(String var_msg) {
		//super(var_msg);
new ObjectNotFoundException(404, "xiiiiiiiiiiiiiiii caterogirasssssss") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Class<? extends Annotation> annotationType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public NotFoundAction action() {
				// TODO Auto-generated method stub
				return null;
			}
		};

//System.out.println("\n\n\nÉ aqui this.toString():"+this.getCause());
		//System.out.println("\n\n\ngetCause().getClass:"+super.getCause().getClass());
		//System.out.println("\n toString:"+super.toString());
		//System.out.println("\n getMessage:"+super.getMessage());
		//System.out.println("\n getClass:"+super.getClass());
		//System.out.println("\n\n\n");
	}

	public Service_Exception_GenericRuntimeException(String var_msg, Throwable var_cause) {
		super(var_msg, var_cause);
	}

}
