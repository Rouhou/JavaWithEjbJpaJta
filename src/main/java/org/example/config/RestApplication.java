package org.example.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.example.resources.ClientResource;
import org.example.resources.CompteResource;
import org.example.resources.OperationResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ClientResource.class);
        classes.add(CompteResource.class);
        classes.add(OperationResource.class);
        return classes;
    }
}