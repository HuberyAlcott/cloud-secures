package ferrous.cloud.general.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class CommonAuthed {

  private Long recordSerial;

  private String memberNaming;

  private String memberCoding;

  private String memberGrants;
}
