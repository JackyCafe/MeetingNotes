-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- 主機: localhost
-- 產生時間： 2017 年 07 月 18 日 14:30
-- 伺服器版本: 5.7.18-0ubuntu0.16.04.1
-- PHP 版本： 7.0.18-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `Meeting`
--

-- --------------------------------------------------------

--
-- 資料表結構 `Notes`
--

CREATE TABLE `Notes` (
  `process_id` int(11) NOT NULL,
  `record_date` date NOT NULL COMMENT '記錄日期',
  `sponsor` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '記錄者',
  `source` varchar(800) COLLATE utf8_bin NOT NULL COMMENT '列管事項 來源',
  `grade` varchar(3) COLLATE utf8_bin NOT NULL,
  `discuss_matter` varchar(3000) COLLATE utf8_bin NOT NULL COMMENT '討論事項',
  `presentation` varchar(3000) COLLATE utf8_bin NOT NULL COMMENT '決議',
  `reference` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '參考',
  `upload` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '上傳路徑'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 資料表的匯出資料 `Notes`
--

INSERT INTO `Notes` (`process_id`, `record_date`, `sponsor`, `source`, `grade`, `discuss_matter`, `presentation`, `reference`, `upload`) VALUES
(1, '2017-07-18', '林彥亨', '<p>106年第3次工作會議</p>\r\n\r\n<p>106/5/9(二)</p>\r\n', 'A01', '<p><strong>建請教育部協助協調縣市政府，將初階認證線上審查作業規劃，訂於</strong><strong>6/1</strong><strong>後進行，提請討論。</strong></p>\r\n\r\n<p>&nbsp;本系統之初階認證縣市審查功能，因系統頁面修正，尚未完善，擬請教育部協助協調各縣市之線上審查期程訂於6/1後系統測試完成並能穩定上線使用為妥(已知高雄市5/17、嘉義市5/31進行縣市審查作業)。</p>\r\n', '<ol>\r\n	<li>精緻網須於各縣市完成初階認證審查人員培訓前，完成初階認證審查功能介面。</li>\r\n	<li>精緻網E-mail通知各縣市教專中心，系統於6月1日開放初階認證審查功能。請教育部發函通知各縣市教專中心審查作業延期。</li>\r\n	<li>學校端推動小組會議紀錄上傳處與初階認證審查功能一併於6月1日上線，未上傳會議記錄不綁定學校初階認證送件功能</li>\r\n</ol>\r\n', '', ''),
(2, '2017-07-18', '林彥亨', '<p>106年第3次工作會議</p>\r\n\r\n<p>106/5/9(二)</p>\r\n', 'A02', '<p><strong>有關</strong><strong>106</strong><strong>年計畫之系統開發相關規劃乙案，提請討論。</strong><strong>(</strong><strong>請參照</strong><strong>106</strong><strong>開發相關事項</strong><strong>)</strong><strong>。</strong></p>\r\n\r\n<ol>\r\n	<li>\r\n	<p>旨揭期程預估自從四月底開始啟動需求訪談，作為系統建置規格書之參考架構，原規劃開發期程如106開發事項說明。</p>\r\n	</li>\r\n	<li>\r\n	<p>本計畫開發相關功能除依政策需求規劃建置內容，開發期程與進度配合政策調整而預定；原規劃配合十二年國教課綱之公開授課與專業回饋及分析，於本(106)年8月測試上線，惟報載107課綱延至108年實施，該功能開發進程擬因應調整外，而其他規畫另配合教育部需求進行之。</p>\r\n	</li>\r\n	<li>\r\n	<p>有關需求訪談與開發流程，諮詢訪談對象分為四部分，師培機構、學校端、教師端及縣市政府(或教專中心)端，學校端委請美珠校長擔任窗口，教師端為久晉老師，並已由教育部提供團隊教專中心及師培大學諮詢名單進而規劃。相關開發作業預訂期程規劃如106開發事項說明(附件)。</p>\r\n	</li>\r\n</ol>\r\n\r\n<p>綜上，106年計畫，依原定撰寫規格書(三個月)、開發(三個月)、測試與修正(一個月)、上線與回饋(二個月)之進度，除功能內容依實際需求進行增減，其餘依原規劃辦理相關事宜。</p>\r\n', '<p>目前暫定為105年4-6月撰寫規格書，7-9月開發系統，10月測試與修正，11-12月上線與回饋。</p>\r\n\r\n<p>因精緻網目前資訊人力聘用不順致人力有限，恐影響規格書撰寫與開發原規劃之進度，待內部就人力與諮詢規模及諮詢人員時間合理檢視相關期程後，兩周內向教育部提出相關資料。</p>\r\n', '', ''),
(3, '2017-07-18', '林彥亨', '<p>106年第1次工作會議</p>\r\n\r\n<p>106/3/14(二)</p>\r\n', 'A02', '<p><strong>&nbsp;有關精緻網</strong><strong>106</strong><strong>年的計畫內容，開發之需求所需協助事宜，提請討論。</strong></p>\r\n\r\n<p>一、106年計劃所需系統改版、升級之相關開發，除依計畫書所定內容(新增項目)，開發程序須經需求訪談、需求確認、模擬畫面檢視、開發、測試、上線等流程開發流程</p>\r\n\r\n<p>二、為能辦理後續招標及確保需求穩定，擬於本(106)年6月完成規格書編寫；為配合政策需求安排開發期程，及本團隊之人力調度，因此，請教育部於106年3月31日前提供以下協助:</p>\r\n\r\n<p>(一)系統內容及功能需求期程順序</p>\r\n\r\n<p>(二)各功能、角色需求訪談建議人員或原則</p>\r\n\r\n<p>(三)各開發階段之確認程序與作業規範</p>\r\n\r\n<p>(四)系統開發需教育部協助事項。</p>\r\n', '<p>一、先請精緻網團隊以106年所提計畫書架構為本(105年系統內容及106政策需求)，於兩週內提供予教育部檢視；如有不足或需調整需求，由教育部完成處理後，提供精緻網彙整，並於下次工作會議進行報告確認之。</p>\r\n\r\n<p>二、所提開發所需程序及需求訪談之建議人員，由承辦人與精緻網團隊溝通確認之。</p>\r\n', '', ''),
(4, '2017-07-18', '林彥亨', '<p>106年第3次工作會議</p>\r\n\r\n<p>106/5/9(二)</p>\r\n', 'A03', '<p><strong>配合教育部教專評鑑轉型，涉及本平台相關事宜，提請討論。</strong></p>\r\n\r\n<p>有關本網站內「評鑑」二字如何修改</p>\r\n', '<p>一、精緻網現有名詞暫不變動，待實踐方案發布後，逐一修正至106系統。</p>\r\n\r\n<p>二、專業回饋人才等相關名詞，部內已確認，請8/1和網站名稱同步更新至精緻網。</p>\r\n', '', '');

-- --------------------------------------------------------

--
-- 資料表結構 `Process`
--

CREATE TABLE `Process` (
  `id` int(11) NOT NULL,
  `process_id` int(11) NOT NULL,
  `reply_date` date NOT NULL,
  `sponsor` varchar(16) COLLATE utf8_bin NOT NULL,
  `status` varchar(3000) COLLATE utf8_bin NOT NULL,
  `presestation` varchar(30) COLLATE utf8_bin NOT NULL,
  `reference` varchar(200) COLLATE utf8_bin NOT NULL,
  `upload` varchar(200) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- 資料表結構 `User`
--

CREATE TABLE `User` (
  `id` int(11) NOT NULL,
  `user` varchar(32) COLLATE utf8_bin NOT NULL,
  `passwd` varchar(32) COLLATE utf8_bin NOT NULL,
  `ChineseName` varchar(32) COLLATE utf8_bin NOT NULL,
  `email` varchar(60) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 資料表的匯出資料 `User`
--

INSERT INTO `User` (`id`, `user`, `passwd`, `ChineseName`, `email`) VALUES
(1, 'Jacky', '1qaz', '林彥亨', '');

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `Notes`
--
ALTER TABLE `Notes`
  ADD PRIMARY KEY (`process_id`),
  ADD UNIQUE KEY `id` (`process_id`);

--
-- 資料表索引 `Process`
--
ALTER TABLE `Process`
  ADD PRIMARY KEY (`id`),
  ADD KEY `process_id` (`process_id`) USING BTREE;

--
-- 資料表索引 `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`id`);

--
-- 在匯出的資料表使用 AUTO_INCREMENT
--

--
-- 使用資料表 AUTO_INCREMENT `Notes`
--
ALTER TABLE `Notes`
  MODIFY `process_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- 使用資料表 AUTO_INCREMENT `Process`
--
ALTER TABLE `Process`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用資料表 AUTO_INCREMENT `User`
--
ALTER TABLE `User`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- 已匯出資料表的限制(Constraint)
--

--
-- 資料表的 Constraints `Process`
--
ALTER TABLE `Process`
  ADD CONSTRAINT `process_fk` FOREIGN KEY (`process_id`) REFERENCES `Notes` (`process_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
