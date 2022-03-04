package uk.m0nom.location.coords;

import java.util.regex.Pattern;

public interface LocationParser {
    Pattern getPattern();
    GlobalCoords3D parse(LocationSource source, String location);
    String getName();
}
