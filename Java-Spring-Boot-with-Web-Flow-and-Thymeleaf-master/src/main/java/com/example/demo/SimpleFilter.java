package com.example.demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

//@Component
public class SimpleFilter implements Filter {
	
	public static final String POLICY = "default-src 'self'; script-src 'self' 'sha256-KbAKvc572bZewq9SuQiS04fQZ7rtGU87ZAHB1IG9wHs=' 'sha256-67Poi97W2wULU0ePQH6VWJK08o+EL0DtbKxdCDc0fVM=';";

	
   @Override
   public void destroy() {}

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain) 
      throws IOException, ServletException {
	   /*
      if (response instanceof HttpServletResponse) 
      {
      	((HttpServletResponse)response).setHeader("Content-Security-Policy", POLICY);
  	  }*/
      filterchain.doFilter(request, response);
   }

   @Override
   public void init(FilterConfig filterconfig) throws ServletException {}
}