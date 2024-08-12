public class Train {
    private String departureTime;
    private String originName;
    private String destinationName;
    private String platform;
    private String trainUid;

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setTrainUid(String trainUid) {
        this.trainUid = trainUid;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getOriginName() {
        return originName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public String getPlatform() {
        return platform;
    }

    public String getTrainUid() {
        return trainUid;
    }

    public String toString() {
        return String.format("Departs at %s from %s to %s, Platform: %s, Train UID: %s",
                departureTime, originName, destinationName, platform, trainUid);
    }
}

