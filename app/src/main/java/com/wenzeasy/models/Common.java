package com.wenzeasy.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Common {

    public static DatabaseReference defaultRef = FirebaseDatabase.getInstance().getReference();
}
