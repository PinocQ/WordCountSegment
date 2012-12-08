package org.nlp.lucene;

import java.io.IOException;
import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.ResourceLoader;
import org.apache.lucene.analysis.util.ResourceLoaderAware;
import org.apache.lucene.analysis.util.TokenFilterFactory;

public class AllSplitFilterFactory extends TokenFilterFactory implements
		ResourceLoaderAware {

	@Override
	public void init(Map<String, String> args) {
		super.init(args);
		assureMatchVersion();
	}

	@Override
	public void inform(ResourceLoader arg0) throws IOException {
		String dicPath = args.get("dicPath");
		String forwardbool=args.get("isForward");
		boolean isForward=false;
		if (dicPath == null || dicPath.isEmpty()) {
			dicPath = "SogouLabDic.dic";
		}
		if(forwardbool != null && !forwardbool.isEmpty()){
			isForward=Boolean.parseBoolean(forwardbool);
		}
		AllSplitSegmenter.init(dicPath,isForward);
	}

	public TokenStream create(TokenStream input) {
		return new AllSplitFilter(input);
	}

}
