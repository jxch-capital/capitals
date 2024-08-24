package io.github.jxch.capitals.stock4j.yahoo.quote;

import io.github.jxch.capitals.crawler.common.UrlParamSupport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.HttpUrl;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YahooQuoteParam implements UrlParamSupport {
    private List<String> symbols;

    public String getSymbolsParam() {
        return String.join(",", symbols);
    }

    @Override
    public HttpUrl toUrl(HttpUrl.Builder builder) {
        return builder.addQueryParameter("symbols", getSymbolsParam()).build();
    }

}
