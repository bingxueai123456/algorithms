package com.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: eclair
 * @Date: 2019/12/17 23:16
 * @Description:
 */
public class TestEasyExcel {
	@Test
	public void test1() {
		String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
		EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
	}

	@Test
	public void excludeOrIncludeWrite() {
		String fileName = TestFileUtil.getPath() + "excludeOrIncludeWrite" + System.currentTimeMillis() + ".xlsx";

		// 根据用户传入字段 假设我们要忽略 date
		Set<String> excludeColumnFiledNames = new HashSet<String>();
		excludeColumnFiledNames.add("date");
		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		EasyExcel.write(fileName, DemoData.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("模板")
				.doWrite(data());

//		fileName = TestFileUtil.getPath() + "excludeOrIncludeWrite" + System.currentTimeMillis() + ".xlsx";
//		// 根据用户传入字段 假设我们只要导出 date
//		Set<String> includeColumnFiledNames = new HashSet<String>();
//		includeColumnFiledNames.add("date");
//		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//		EasyExcel.write(fileName, DemoData.class).includeColumnFiledNames(includeColumnFiledNames).sheet("模板")
//				.doWrite(data());
	}


	@Test
	public void indexWrite() {
		String fileName = TestFileUtil.getPath() + "indexWrite" + System.currentTimeMillis() + ".xlsx";
		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		EasyExcel.write(fileName, IndexData.class).sheet("模板").doWrite(data());
	}

	@Test
	public void complexHeadWrite() {
		String fileName = TestFileUtil.getPath() + "complexHeadWrite" + System.currentTimeMillis() + ".xlsx";
		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		EasyExcel.write(fileName, ComplexHeadData.class).sheet("模板").doWrite(data());
	}

	@Test
	public void repeatedWrite() {
		// 方法1 如果写到同一个sheet
		String fileName = TestFileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
		// 这里 需要指定写用哪个class去写
		ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
		// 这里注意 如果同一个sheet只要创建一次
		WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
		// 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
		for (int i = 0; i < 5; i++) {
			// 分页去数据库查询数据 这里可以去数据库查询每一页的数据
			List<DemoData> data = data();
			excelWriter.write(data, writeSheet);
		}
		/// 千万别忘记finish 会帮忙关闭流
		excelWriter.finish();

		// 方法2 如果写到不同的sheet 同一个对象
		fileName = TestFileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
		// 这里 指定文件
		excelWriter = EasyExcel.write(fileName, DemoData.class).build();
		// 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
		for (int i = 0; i < 5; i++) {
			// 每次都要创建writeSheet 这里注意必须指定sheetNo
			writeSheet = EasyExcel.writerSheet(i, "模板").build();
			// 分页去数据库查询数据 这里可以去数据库查询每一页的数据
			List<DemoData> data = data();
			excelWriter.write(data, writeSheet);
		}
		/// 千万别忘记finish 会帮忙关闭流
		excelWriter.finish();

		// 方法3 如果写到不同的sheet 不同的对象
		fileName = TestFileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
		// 这里 指定文件
		excelWriter = EasyExcel.write(fileName).build();
		// 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
		for (int i = 0; i < 5; i++) {
			// 每次都要创建writeSheet 这里注意必须指定sheetNo。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
			writeSheet = EasyExcel.writerSheet(i, "模板").head(DemoData.class).build();
			// 分页去数据库查询数据 这里可以去数据库查询每一页的数据
			List<DemoData> data = data();
			excelWriter.write(data, writeSheet);
		}
		/// 千万别忘记finish 会帮忙关闭流
		excelWriter.finish();
	}

	@Test
	public void converterWrite() {
		String fileName = TestFileUtil.getPath() + "converterWrite" + System.currentTimeMillis() + ".xlsx";
		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		EasyExcel.write(fileName, ComplexHeadData.class).sheet("模板").doWrite(data());
	}


	@Test
	public void templateWrite() {
		String templateFileName = TestFileUtil.getPath() +  "demo.xlsx";
		String fileName = TestFileUtil.getPath() + "templateWrite" + System.currentTimeMillis() + ".xlsx";
		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		EasyExcel.write(fileName, ComplexHeadData.class).withTemplate(templateFileName).sheet().doWrite(data());
	}

	@Test
	public void widthAndHeightWrite() {
		String fileName = TestFileUtil.getPath() + "widthAndHeightWrite" + System.currentTimeMillis() + ".xlsx";
		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		EasyExcel.write(fileName, WidthAndHeightData.class).sheet("模板").doWrite(data());
	}

	@Test
	public void styleWrite() {
		String fileName = TestFileUtil.getPath() + "styleWrite" + System.currentTimeMillis() + ".xlsx";
		// 头的策略
		WriteCellStyle headWriteCellStyle = new WriteCellStyle();
		// 背景设置为红色
		headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		WriteFont headWriteFont = new WriteFont();
		headWriteFont.setFontHeightInPoints((short)20);
		headWriteCellStyle.setWriteFont(headWriteFont);
		// 内容的策略
		WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
		// 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
		contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
		// 背景绿色
		contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		WriteFont contentWriteFont = new WriteFont();
		// 字体大小
		contentWriteFont.setFontHeightInPoints((short)20);
		contentWriteCellStyle.setWriteFont(contentWriteFont);
		// 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
		HorizontalCellStyleStrategy horizontalCellStyleStrategy =
				new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		EasyExcel.write(fileName, DemoData.class).registerWriteHandler(horizontalCellStyleStrategy).sheet("模板")
				.doWrite(data());
	}

	@Test
	public void mergeWrite() {
		String fileName = TestFileUtil.getPath() + "mergeWrite" + System.currentTimeMillis() + ".xlsx";
		// 每隔2行会合并 把eachColumn 设置成 3 也就是我们数据的长度，所以就第一列会合并。当然其他合并策略也可以自己写
		LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(1, 2,1);
		// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
		EasyExcel.write(fileName, DemoData.class).registerWriteHandler(loopMergeStrategy).sheet("模板").doWrite(data());
	}

	@Test
	public void tableWrite() {
		String fileName = TestFileUtil.getPath() + "tableWrite" + System.currentTimeMillis() + ".xlsx";
		// 这里直接写多个table的案例了，如果只有一个 也可以直一行代码搞定，参照其他案例
		// 这里 需要指定写用哪个class去写
		ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
		// 把sheet设置为不需要头 不然会输出sheet的头 这样看起来第一个table 就有2个头了
		WriteSheet writeSheet = EasyExcel.writerSheet("模板").needHead(Boolean.FALSE).build();
		// 这里必须指定需要头，table 会继承sheet的配置，sheet配置了不需要，table 默认也是不需要
		WriteTable writeTable0 = EasyExcel.writerTable(0).needHead(Boolean.TRUE).build();
		WriteTable writeTable1 = EasyExcel.writerTable(1).needHead(Boolean.TRUE).build();
		// 第一次写入会创建头
		excelWriter.write(data(), writeSheet, writeTable0);
		// 第二次写如也会创建头，然后在第一次的后面写入数据
		excelWriter.write(data(), writeSheet, writeTable1);
		/// 千万别忘记finish 会帮忙关闭流
		excelWriter.finish();
	}


	private List<List<Object>> dataList() {
		List<List<Object>> list = new ArrayList<List<Object>>();
		for (int i = 0; i < 10; i++) {
			List<Object> data = new ArrayList<Object>();
			data.add("字符串" + i);
			data.add(new Date());
			data.add(0.56);
			list.add(data);
		}
		return list;
	}

	private List<DemoData> data() {
		List<DemoData> list = new ArrayList<DemoData>();
		for (int i = 0; i < 10; i++) {
			DemoData data = new DemoData();
			data.setString("字符串" + i);
			data.setDate(new Date());
			data.setDoubleData(0.56);
			list.add(data);
		}
		return list;
	}
}
