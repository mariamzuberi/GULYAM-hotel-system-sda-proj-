
package hotel.management.system;
import java.util.*;

public class CustomerDataModel {
    private List<Observer> observers = new ArrayList<>();

    // Notify all observers
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update();
        }
    }

    // Register an observer
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    // Example method: called when data changes
    public void customerDataChanged() {
        
        notifyObservers();  // Notify GUI to refresh
    }
}
