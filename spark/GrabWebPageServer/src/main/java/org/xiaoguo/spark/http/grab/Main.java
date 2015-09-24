package org.xiaoguo.spark.http.grab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xiaoguo.spark.http.util.HttpUtils;

public class Main {

	public static void main(String[] args) {
		newFixedThreadPool.submit(()->{System.out.println("start!");});
		load("http://blog.csdn.net/hustpzb/article/details/8230454/");
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {

			}

		}
		// System.out.println(count);
	}

	private static Map<String, Integer> count = new HashMap<String, Integer>();

	private static Queue<Document> queue = new LinkedBlockingQueue<Document>();
	private static AtomicLong ids=new AtomicLong();
	private static  ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(99);

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
			newFixedThreadPool.execute(new Runnable() {
				final Document poll=doGet.document;
				@Override
				public void run() {
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
				}
			});

		}
	}

}
