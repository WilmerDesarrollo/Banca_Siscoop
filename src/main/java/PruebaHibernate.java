
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.RuntimeType;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author root
 */
public class PruebaHibernate {
         public static void main(String[] args) {
             System.out.println("|Iniciando...");
             Configuration cfg=new Configuration() {
                 @Override
                 public RuntimeType getRuntimeType() {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 @Override
                 public Map<String, Object> getProperties() {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 @Override
                 public Object getProperty(String name) {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 @Override
                 public Collection<String> getPropertyNames() {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 @Override
                 public boolean isEnabled(Feature feature) {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 @Override
                 public boolean isEnabled(Class<? extends Feature> featureClass) {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 @Override
                 public boolean isRegistered(Object component) {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 @Override
                 public boolean isRegistered(Class<?> componentClass) {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 @Override
                 public Map<Class<?>, Integer> getContracts(Class<?> componentClass) {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 @Override
                 public Set<Class<?>> getClasses() {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }

                 @Override
                 public Set<Object> getInstances() {
                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 }
             };
    }
}
