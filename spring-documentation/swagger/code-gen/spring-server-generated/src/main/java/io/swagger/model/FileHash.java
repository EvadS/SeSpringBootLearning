package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Хэши файла.
 */
@ApiModel(description = "Хэши файла.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-23T14:28:08.947Z[GMT]")
public class FileHash   {
  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("filename")
  private String filename = null;

  @JsonProperty("sha256")
  private String sha256 = null;

  @JsonProperty("md5")
  private String md5 = null;

  public FileHash userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Код пользователя, добавившего файл.
   * @return userId
  **/
  @ApiModelProperty(value = "Код пользователя, добавившего файл.")
  
    public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public FileHash filename(String filename) {
    this.filename = filename;
    return this;
  }

  /**
   * Имя файла.
   * @return filename
  **/
  @ApiModelProperty(value = "Имя файла.")
  
    public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public FileHash sha256(String sha256) {
    this.sha256 = sha256;
    return this;
  }

  /**
   * Хэш SHA256.
   * @return sha256
  **/
  @ApiModelProperty(value = "Хэш SHA256.")
  
    public String getSha256() {
    return sha256;
  }

  public void setSha256(String sha256) {
    this.sha256 = sha256;
  }

  public FileHash md5(String md5) {
    this.md5 = md5;
    return this;
  }

  /**
   * Хэш MD5.
   * @return md5
  **/
  @ApiModelProperty(value = "Хэш MD5.")
  
    public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileHash fileHash = (FileHash) o;
    return Objects.equals(this.userId, fileHash.userId) &&
        Objects.equals(this.filename, fileHash.filename) &&
        Objects.equals(this.sha256, fileHash.sha256) &&
        Objects.equals(this.md5, fileHash.md5);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, filename, sha256, md5);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileHash {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    filename: ").append(toIndentedString(filename)).append("\n");
    sb.append("    sha256: ").append(toIndentedString(sha256)).append("\n");
    sb.append("    md5: ").append(toIndentedString(md5)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
