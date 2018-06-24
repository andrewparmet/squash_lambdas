package com.parmet.squashlambdas.match;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.parmet.squashlambdas.match.Sport.HARDBALL;
import static com.parmet.squashlambdas.match.Sport.RACQUETS;
import static com.parmet.squashlambdas.match.Sport.SQUASH;
import static com.parmet.squashlambdas.match.Sport.TENNIS;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Court {
  COURT_1(SQUASH),
  COURT_2(SQUASH),
  COURT_3(SQUASH),
  COURT_5(HARDBALL),
  COURT_6(HARDBALL),
  COURT_7(HARDBALL),
  TENNIS_COURT(TENNIS),
  RACQUETS_COURT(RACQUETS);

  private static final Pattern PATTERN = Pattern.compile(".*Court: Court #(\\d) [/\\-].*");

  private final Sport sport;

  private Court(Sport sport) {
    this.sport = checkNotNull(sport, "sport");
  }

  /** e.g., "Tennis & Racquet Club / Court: Court #7" */
  public static Court fromLocationString(String body) {
    Matcher m = PATTERN.matcher(body);
    checkArgument(m.matches(), "Unable to parse court from %s", body);
    return valueOf("COURT_" + Integer.parseInt(m.group(1)));
  }

  public Sport getSport() {
    return sport;
  }
}
