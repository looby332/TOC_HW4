import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.net.*;
import org.json.*;

public class TocHw4 {
	public static void main(String[] args) throws Exception {
		URL url = new URL(args[0]);
		URLConnection connect = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connect.getInputStream(), "UTF-8"));
		String inputLine;

		String address = null;
		String a = null;
		int time = 0;
		int price = 0;
		int count = 0;
		String addr[] = new String[2100];
		int h_dollar[] = new int[2100]; // 最高成交價
		int l_dollar[] = new int[2100];
		int date[][] = new int[2100][100];
		int appear[] = new int[2100];
		int array_count = 1;

		while ((inputLine = in.readLine()) != null) { // System.out.println(inputLine);

			if (count != 0 && inputLine.charAt(0) == '{') {
				JSONTokener toke = new JSONTokener(inputLine);
				JSONObject houseOBJ;
				try {
					houseOBJ = (JSONObject) toke.nextValue();
					address = houseOBJ.getString("土地區段位置或建物區門牌");
					time = houseOBJ.getInt("交易年月");
					price = houseOBJ.getInt("總價元");

					String patt_address = ".*路";
					Pattern pattern_address = Pattern.compile(patt_address);
					Matcher match = pattern_address.matcher(address);
					String patt_address2 = ".*大道";
					Pattern pattern_address2 = Pattern.compile(patt_address2);
					Matcher match2 = pattern_address2.matcher(address);
					String patt_address3 = ".*街";
					Pattern pattern_address3 = Pattern.compile(patt_address3);
					Matcher match3 = pattern_address3.matcher(address);

					while (match.find()) {
						if (array_count == 1) { // 第一個存入陣列的值
							addr[array_count] = match.group();
							h_dollar[array_count] = price;
							l_dollar[array_count] = price;
							date[array_count][0] = time;
							appear[array_count] = 0;
							appear[array_count]++;
							array_count++;
						} else {
							for (int i = 1; i < array_count + 1; i++) {
								String mat1 = "";
								String mat = addr[i];
								String mat2 = match.group();
								if (mat2.equals(mat)) { // 地址是否相等
									int j = 0;
									if (price >= h_dollar[i])
										h_dollar[i] = price;
									if (price < l_dollar[i])
										l_dollar[i] = price;
									while (date[i][j] != 0 || j < 100) { // 時間是否相等
										if (date[i][j] == time) {
											break;
										}
										j++;
										if (date[i][j] == 0) {
											date[i][j] = time;
											appear[i]++;
										}
									}
									break;
								} else if (i == array_count) {
									addr[i] = match.group();
									h_dollar[i] = price;
									l_dollar[i] = price;
									date[i][0] = time;
									appear[i] = 0;
									appear[i]++;
									array_count++;
									break;
								}
							}
						}
					}
					if (!match.find()) {
						while (match2.find()) {
							if (array_count == 1) { // 第一個存入陣列的值
								addr[array_count] = match2.group();
								h_dollar[array_count] = price;
								l_dollar[array_count] = price;
								date[array_count][0] = time;
								appear[array_count] = 0;
								appear[array_count]++;
								array_count++;
							} else {
								for (int i = 1; i < array_count + 1; i++) {
									String mat = addr[i];
									String mat2 = match2.group();
									if (mat2.equals(mat)) { // 地址是否相等
										int j = 0;
										if (price >= h_dollar[i])
											h_dollar[i] = price;
										if (price < l_dollar[i])
											l_dollar[i] = price;
										while (date[i][j] != 0 || j < 100) { // 時間是否相等
											if (date[i][j] == time) {
												break;
											}
											j++;
											if (date[i][j] == 0) {
												date[i][j] = time;
												appear[i]++;
											}
										}
										break;
									} else if (i == array_count) {
										addr[i] = match2.group();
										h_dollar[i] = price;
										l_dollar[i] = price;
										date[i][0] = time;
										appear[i] = 0;
										appear[i]++;
										array_count++;
										break;
									}
								}
							}
						}
					}
					if (!match.find() && !match2.find()) {
						while (match3.find()) {
							if (array_count == 1) { // 第一個存入陣列的值
								addr[array_count] = match3.group();
								h_dollar[array_count] = price;
								l_dollar[array_count] = price;
								date[array_count][0] = time;
								appear[array_count] = 0;
								appear[array_count]++;
								array_count++;
							} else {
								for (int i = 1; i < array_count + 1; i++) {
									String mat = addr[i];
									String mat2 = match3.group();
									if (mat2.equals(mat)) { // 地址是否相等
										int j = 0;
										if (price >= h_dollar[i])
											h_dollar[i] = price;
										if (price < l_dollar[i])
											l_dollar[i] = price;
										while (date[i][j] != 0 || j < 100) { // 時間是否相等
											if (date[i][j] == time) {
												break;
											}
											j++;
											if (date[i][j] == 0) {
												date[i][j] = time;
												appear[i]++;
											}
										}
										break;
									} else if (i == array_count) {
										addr[i] = match3.group();
										h_dollar[i] = price;
										l_dollar[i] = price;
										date[i][0] = time;
										appear[i] = 0;
										appear[i]++;
										array_count++;
										break;
									}
								}
							}
						}
					} else if (!match.find() && !match2.find()
							&& !match3.find()) {
						String patt_address4 = ".*巷";
						Pattern pattern_address4 = Pattern
								.compile(patt_address4);
						Matcher match4 = pattern_address4.matcher(address);
						while (match4.find()) {
							if (array_count == 1) { // 第一個存入陣列的值
								addr[array_count] = match4.group();
								h_dollar[array_count] = price;
								l_dollar[array_count] = price;
								date[array_count][0] = time;
								appear[array_count] = 0;
								appear[array_count]++;
								array_count++;
							} else {
								for (int i = 1; i < array_count + 1; i++) {
									String mat = addr[i];
									String mat2 = match4.group();
									if (mat2.equals(mat)) { // 地址是否相等
										int j = 0;
										if (price >= h_dollar[i])
											h_dollar[i] = price;
										if (price < l_dollar[i])
											l_dollar[i] = price;
										while (date[i][j] != 0 || j < 100) { // 時間是否相等
											if (date[i][j] == time) {
												break;
											}
											j++;
											if (date[i][j] == 0) {
												date[i][j] = time;
												appear[i]++;
											}
										}
										break;
									} else if (i == array_count) {
										addr[i] = match4.group();
										h_dollar[i] = price;
										l_dollar[i] = price;
										date[i][0] = time;
										appear[i] = 0;
										appear[i]++;
										array_count++;
										break;
									}
								}
							}
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			count++;
		}
		in.close();
		int i = 2;
		int k = 0; // 計算有幾個appear量相同
		int max = appear[1];
		int maxID[] = new int[2010];
		maxID[1] = 1;
		while (i <= array_count) {
			if (appear[i] > max) {
				max = appear[i];
				maxID[0] = i;
				int j = 1;
				while (maxID[j] != 0) {
					maxID[j] = 0;
					j++;
				}
			} else if (appear[i] == max) {
				int j = 0;
				while (maxID[j] != 0)
					j++;
				maxID[j] = i;
			}
			i++;
		}
		while (maxID[k] != 0) {
			System.out.println(addr[maxID[k]] + ", 最高成交價: "
					+ h_dollar[maxID[k]] + ", 最低成交價: " + l_dollar[maxID[k]]);
			k++;
		}
	}
}

