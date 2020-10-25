package com.mvvm.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mvvm.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user")
    void deleteAllUsers();

    @Query("SELECT * FROM User ORDER BY id ASC")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM User A WHERE A.userName=:userName AND A.password=:password")
    User getUser(String userName, String password);
}
