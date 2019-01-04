package ir.iut.adb.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by rasool on 12/23/2018.
 * Email: Rasoul.Samani@gmail.com
 */
@Setter
@Getter
@AllArgsConstructor
public class NodeTuple {

    private String source;
    private String target;
    private String kind;
    private String direction;

}
