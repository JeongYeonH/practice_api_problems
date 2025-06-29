package com.yeonny.demo.CookieChat;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomClientKey {
    private String clientId;
    private String roomId;

    public RoomClientKey(String clientId, String roomId){
        this.clientId = clientId;
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        RoomClientKey that = (RoomClientKey) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(roomId, that.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, roomId);
    }
}
