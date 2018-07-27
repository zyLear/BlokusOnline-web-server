package com.zylear.blokus.wsserver.bean.transfer;

import java.util.List;

/**
 * Created by xiezongyu on 2018/7/27.
 */
public class RoomListMsg {

    private List<RoomItemMsg> roomItems;

    public List<RoomItemMsg> getRoomItems() {
        return roomItems;
    }

    public void setRoomItems(List<RoomItemMsg> roomItems) {
        this.roomItems = roomItems;
    }

    public static class RoomItemMsg {
        private String roomName;
        private Integer maxPlayerCount;
        private Integer RoomStatus;
        private Integer currentPlayerCount;

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public Integer getMaxPlayerCount() {
            return maxPlayerCount;
        }

        public void setMaxPlayerCount(Integer maxPlayerCount) {
            this.maxPlayerCount = maxPlayerCount;
        }

        public Integer getRoomStatus() {
            return RoomStatus;
        }

        public void setRoomStatus(Integer roomStatus) {
            RoomStatus = roomStatus;
        }

        public Integer getCurrentPlayerCount() {
            return currentPlayerCount;
        }

        public void setCurrentPlayerCount(Integer currentPlayerCount) {
            this.currentPlayerCount = currentPlayerCount;
        }
    }

}
