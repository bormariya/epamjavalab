package model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Contact {
    private long id;
    private String content;
    @Builder.Default
    private String type;
}
