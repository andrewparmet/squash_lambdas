package com.parmet.squashlambdas.s3;

import static com.google.common.truth.Truth.assertThat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.parmet.squashlambdas.testutil.TestUtils;
import java.time.Instant;
import java.util.Map;
import org.junit.Test;

public class S3EmailNotificationTest {
  private static final Gson GSON = new Gson();

  @Test
  public void fromInputObjectTest() throws Exception {
    Object obj = inputFromFile("s3CreateObject.json");
    S3EmailNotification note = S3EmailNotification.fromInputObject(obj);
    S3EmailNotification expected =
        new S3EmailNotification(
            "2.0",
            "aws:s3",
            "us-east-1",
            Instant.parse("2018-03-26T00:27:50.117Z"),
            "ObjectCreated:Put",
            new S3CreateObjectInfo(
                new BucketInfo("parmet-squash-emails"),
                new S3ObjectInfo("emails/srnvood65ihat8t9o7m1p1fjaiqumeq4do1p3bo1")));
    assertThat(note).isEqualTo(expected);
  }

  public static Object inputFromFile(String filename) {
    return GSON.fromJson(
        TestUtils.getJsonResourceAsString(filename),
        new TypeToken<Map<String, Object>>() {}.getType());
  }
}
