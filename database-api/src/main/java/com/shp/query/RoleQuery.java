package com.shp.query;

import lombok.Data;

/**
 * Created by Shape on 2018/4/21.
 */
@Data
public class RoleQuery extends BaseQuery{
    private String userName;
    private String userQQ;
    private String userPhone;
    private String userEmail;
}
