package org.xiaoguo.spark.http.grab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xiaoguo.spark.http.util.HttpUtils;

public class Main {

	public static void main(String[] args) {
		load("http://blog.csdn.net/hustpzb/article/details/8230454/");
		while (true) {
			try {
				Document poll = queue.poll();

				Elements taga = poll.getElementsByTag("a");
				String text = poll.text();
				Stream<Element> filter = taga.stream().filter(ele -> {
					String attr = ele.attr("href");
					if (attr.startsWith("#") || attr.startsWith("javascript:")) {
						return false;
					}
					return true;
				});
				List<String> collect = filter.map(ele -> ele.absUrl("href")).collect(Collectors.toList());
				collect.forEach(Main::load);

			} catch (Exception e) {

			}

		}
		// System.out.println(count);
	}

	private static Map<String, Integer> count = new HashMap<String, Integer>();

	private static Queue<Document> queue = new LinkedBlockingQueue<Document>();

	public static void load(String url) {
		if (count.containsKey(url)) {
			count.put(url, count.get(url) + 1);
			return;
		}
		if (url == null || !url.startsWith("http://")) {
			return;
		}
		count.put(url, 1);
		System.out.println("load:" + url);
		HttpResponse doGet = HttpUtils.doPost(url, null);
		if (doGet.stateCode == 200) {
			queue.offer(doGet.document);

		}
	}

}
