package io.github.jxch.capitals.stock4j.api3.yahoo.chart;

import io.github.jxch.capitals.crawler.common.UrlParamSupport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import okhttp3.HttpUrl;

import java.util.Calendar;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class YahooChartUrlParam implements UrlParamSupport {
    private String code;
    private Date start;
    @Builder.Default
    private Date end = Calendar.getInstance().getTime();
    @Builder.Default
    private String interval = "1d";

    public String getPeriod1Param() {
        return String.valueOf(this.start.getTime() / 1000);
    }

    public String getPeriod2Param() {
        return String.valueOf(this.end.getTime() / 1000);
    }

    public String getIntervalParam() {
        return this.interval;
    }

    @Override
    public HttpUrl toUrl(HttpUrl.Builder builder) {
        return builder
                .addPathSegments(getCode())
                .addQueryParameter("period1", getPeriod1Param())
                .addQueryParameter("period2", getPeriod2Param())
                .addQueryParameter("interval", getIntervalParam())
                .build();
    }
}
