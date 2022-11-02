package com.HMCTeam.KeepNotes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private String name;
    private String url;
    private String type;
    private long size;
    private Long noteId;
}
