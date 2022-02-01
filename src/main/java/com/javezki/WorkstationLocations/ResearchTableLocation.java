package com.javezki.WorkstationLocations;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class ResearchTableLocation {
    
    public static List<Location> researchTableLocation = new ArrayList<>();

    public ResearchTableLocation(Location location)
    {
        researchTableLocation.add(location);
    }

    public static List<Location> getlocations()
    {
        return researchTableLocation;
    }
}
