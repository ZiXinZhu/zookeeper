package com.zzx.po;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class UserPO implements Serializable {
    private int id;
    private int age;
    private int high;
}
