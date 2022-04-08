package icu.f2v.kakafarm;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class fundsGroup {
    private List<funds> funds = new ArrayList<>();
}
