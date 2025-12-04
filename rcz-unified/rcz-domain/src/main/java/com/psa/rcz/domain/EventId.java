package com.psa.rcz.domain;

public enum EventId {
    STOLENVEHICLE("StolenVehicle","Stolen","STOLEN"),
    SERVICEINFO("ServiceInfo","ServiceInfo","SERVICEINFO"),
    VEHINFO("VehInfo","VehInfo","VEHINFO"),
    REQUESTSTATE("RequestState","RequestState","REQUESTSTATE"),
    IMMODATA("ImmoData","IMMODATA","STOLEN"),
    VEHALARM("VehAlarm","VehAlarm","REMOTE"),
    IHMREMOTESERVICESACTIVATION("IhmRemoteServicesActivation","IHM","REMOTE"),
    MOTIONALERT("MotionAlert","Alert","REMOTE"),
    LOWPOWERINFO("LowPowerInfo","LowPower","STOLEN"),
    TRACKINGDATA("Tracking/data","Tracking","STOLEN"),
    STATE("state","state","STATE"),
    REMOTEDOORS("RemoteDoors","DOORS","REMOTE"),
    REMOTEENGINE("RemoteEngine","ENGINE","REMOTE");
    private final String label;
    private final String action;
    private final String service;

    EventId(String label, String action, String service) {
        this.label = label;
        this.action = action;
        this.service = service;
    }

    public String getLabel() {
        return label;
    }
    public String getAction() {
        return action;
    }

    public String getService() {
        return service;
    }
    public static EventId fromLabel(String label) {
        for (EventId id : values()) {
            if (id.label.equalsIgnoreCase(label)) {
                return id;
            }
        }
        throw new IllegalArgumentException("No EventId with label: " + label);
    }

}
