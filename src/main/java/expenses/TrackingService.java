package expenses;

import rxjava.Subject;

public class TrackingService {
    private static TrackingService service;
    private static Subject<ItemInfo> deleteItemSubject;
    private static Subject<ItemInfo> editItemSubject;

    private TrackingService() {
        deleteItemSubject = new Subject<>();
        editItemSubject = new Subject<>();
    }

    /**
     * Returns a single instance of the tracking service.
     * @return the singleton
     */
    public static TrackingService getTrackingService() {
        if (service == null) {
            service = new TrackingService();
        }
        return service;
    }

    public void updateDeletionSubject(ItemInfo item) {
        deleteItemSubject.next(item);
    }

    public void updateEditSubject(ItemInfo item) {
        editItemSubject.next(item);
    }

    public static Subject<ItemInfo> getDeleteItemSubject() {
        return deleteItemSubject;
    }

    public static Subject<ItemInfo> getEditItemSubject() {
        return editItemSubject;
    }


}
