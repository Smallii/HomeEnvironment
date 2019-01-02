package com.home.HomeEnvironment.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "file", schema = "family", catalog = "")
public class FileEntity {
    private int id;
    private Integer userId;
    private byte[] file;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "file")
    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileEntity that = (FileEntity) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Arrays.equals(file, that.file);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, userId);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }
}
