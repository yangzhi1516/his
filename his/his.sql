/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : his

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2020-01-17 16:11:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for advice
-- ----------------------------
DROP TABLE IF EXISTS `advice`;
CREATE TABLE `advice` (
  `advice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医嘱编号',
  `advice_information` text COMMENT '医嘱内容',
  `advice_createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `advice_patient_id` int(11) DEFAULT NULL COMMENT '病人编号',
  `advice_staff_id` int(11) DEFAULT NULL COMMENT '写医嘱的医生编号',
  `advice_nurse_id` int(11) DEFAULT NULL COMMENT '执行医嘱的护士编号',
  `advice_state` int(11) DEFAULT '0' COMMENT '是否已经执行医嘱,0:未执行,1:执行',
  `advice_modifydate` datetime DEFAULT NULL COMMENT '执行时间',
  PRIMARY KEY (`advice_id`),
  KEY `fk_a_s` (`advice_staff_id`),
  KEY `fk_a_p` (`advice_patient_id`),
  KEY `fk_a_n` (`advice_nurse_id`),
  CONSTRAINT `fk_a_n` FOREIGN KEY (`advice_nurse_id`) REFERENCES `staff` (`staff_id`),
  CONSTRAINT `fk_a_p` FOREIGN KEY (`advice_patient_id`) REFERENCES `patient` (`patient_id`),
  CONSTRAINT `fk_a_s` FOREIGN KEY (`advice_staff_id`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advice
-- ----------------------------
INSERT INTO `advice` VALUES ('4', '多喝水', '2020-01-17 14:31:07', '3', '1', null, '0', null);

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `attendance_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考勤编号',
  `attendance_createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '签到日期',
  `attendance_staff_id` int(11) DEFAULT NULL COMMENT '签到职员编号',
  `attendance_days` int(11) DEFAULT NULL COMMENT '签到的天数',
  `attendance_state` int(11) DEFAULT '0' COMMENT '0是未签到,1是已签到',
  PRIMARY KEY (`attendance_id`),
  KEY `fk_ad_s` (`attendance_staff_id`),
  CONSTRAINT `fk_ad_s` FOREIGN KEY (`attendance_staff_id`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attendance
-- ----------------------------

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '科室编号',
  `department_name` varchar(255) DEFAULT NULL COMMENT '科室名称',
  `department_number` int(11) DEFAULT NULL COMMENT '科室人数',
  `department_staff_id` int(11) DEFAULT NULL COMMENT '科室主任',
  `department_cost` decimal(10,2) DEFAULT NULL COMMENT '科室对应的挂号费',
  PRIMARY KEY (`department_id`),
  KEY `fk_d_s` (`department_staff_id`),
  CONSTRAINT `fk_d_s` FOREIGN KEY (`department_staff_id`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '呼吸内科', null, null, '50.00');
INSERT INTO `department` VALUES ('2', '心血管内科', null, null, '120.00');
INSERT INTO `department` VALUES ('3', '消化内科', null, null, '60.00');
INSERT INTO `department` VALUES ('4', '血液内科', null, null, '90.00');
INSERT INTO `department` VALUES ('5', '肾内科', null, null, '50.00');
INSERT INTO `department` VALUES ('6', '神经外科', null, null, '70.00');
INSERT INTO `department` VALUES ('7', '泌尿外科', null, null, '100.00');
INSERT INTO `department` VALUES ('8', '心脏外科', null, null, '130.00');
INSERT INTO `department` VALUES ('9', '骨科', null, null, '200.00');
INSERT INTO `department` VALUES ('10', '小儿外科', null, null, '40.00');
INSERT INTO `department` VALUES ('11', '小儿内科', null, null, '60.00');
INSERT INTO `department` VALUES ('12', '儿童保健科', null, null, '30.00');
INSERT INTO `department` VALUES ('13', '妇科', null, null, '300.00');
INSERT INTO `department` VALUES ('14', '产科', null, null, '400.00');
INSERT INTO `department` VALUES ('15', '眼科', null, null, '250.00');
INSERT INTO `department` VALUES ('16', '耳鼻喉科', null, null, '200.00');
INSERT INTO `department` VALUES ('17', '口腔科', null, null, '160.00');
INSERT INTO `department` VALUES ('18', '传染科', null, null, '700.00');
INSERT INTO `department` VALUES ('19', '肿瘤科', null, null, '1000.00');
INSERT INTO `department` VALUES ('20', '康复科', null, null, '80.00');

-- ----------------------------
-- Table structure for drug
-- ----------------------------
DROP TABLE IF EXISTS `drug`;
CREATE TABLE `drug` (
  `drug_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '药物编号',
  `drug_name` varchar(255) DEFAULT NULL COMMENT '药物名称',
  `drug_number` int(11) DEFAULT NULL COMMENT '药物库存',
  `drug_price` decimal(10,2) DEFAULT NULL COMMENT '药品价格',
  `drug_description` text COMMENT '药物描述',
  `drug_manufacturer_id` int(255) DEFAULT NULL COMMENT '药物生产商',
  `drug_modifydate` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '库存变化时更新',
  `drug_createdate` datetime DEFAULT NULL COMMENT '记录进药的时间',
  PRIMARY KEY (`drug_id`),
  KEY `fk_drug_manu` (`drug_manufacturer_id`),
  CONSTRAINT `fk_drug_manu` FOREIGN KEY (`drug_manufacturer_id`) REFERENCES `manufacturer` (`manufacturer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of drug
-- ----------------------------
INSERT INTO `drug` VALUES ('1', '可待因片', '117', '53.00', '【功效主治】养阴敛肺，止咳祛痰。用于支气管炎咳嗽。', '1', '2020-01-17 14:29:45', null);
INSERT INTO `drug` VALUES ('2', '复方甘草片', '138', '23.00', '【用法用量】口服或含化。成人一次3-4片，一日3次。', '4', '2020-01-17 14:29:44', null);
INSERT INTO `drug` VALUES ('3', '盐酸西替利嗪片', '130', '49.00', '【功能主治】季节性鼻炎、常年性过敏性鼻炎、过敏性结膜炎及过敏引起的瘙痒和荨麻疹的对症治疗。', '2', '2020-01-16 10:02:43', null);
INSERT INTO `drug` VALUES ('4', '多潘立酮片', '130', '70.00', '【用法用量】口服，成人一次1片，一日3次，饭前15-30分钟服用。', '5', '2020-01-16 10:09:06', null);
INSERT INTO `drug` VALUES ('5', '小儿牛黄清肺散', '200', '75.00', '用法用量 :口服，周岁以内一次0.5克，一至三岁一次0.5克～1克，一日2次。', '3', '2020-01-16 10:13:33', null);
INSERT INTO `drug` VALUES ('6', '明目地黄丸', '300', '53.00', '功能主治 :滋肾，养肝，明目。用于肝肾阴虚，目涩畏光，视物模糊，迎风流泪。', '7', '2020-01-16 10:16:28', null);
INSERT INTO `drug` VALUES ('7', '	小儿肠胃康颗粒', '260', '60.00', '功能主治	清热平肝，调理脾胃。用于小儿营养紊乱所引起的食欲不振，面色无华，精神烦忧，夜寝哭啼，腹泻腹胀。', '6', '2020-01-16 10:19:38', null);
INSERT INTO `drug` VALUES ('8', '复方酮康唑软膏', '350', '56.00', '【适应症/主治功能】\r\n本品主要用于皮肤浅表真菌感染，如手癣、足癣、体癣、股癣等。', '8', '2020-01-16 10:21:35', null);
INSERT INTO `drug` VALUES ('9', '普乐安胶囊', '320', '62.00', '【适应症/主治功能】\r\n补肾固本。用于肾气不固所致的腰膝酸软，尿后余沥或失禁，及慢性前列腺炎、前列腺增生具有上述症候者。', '9', '2020-01-16 10:23:18', null);
INSERT INTO `drug` VALUES ('10', '复方嗜酸乳杆菌片', '260', '70.00', '【用法用量】 口服。成人一次0.5～1g，一日3次。', '10', '2020-01-16 10:24:54', null);
INSERT INTO `drug` VALUES ('11', '维U颠茄铝镁片Ⅱ', '300', '80.00', '功能主治 :适用于胃及十二指肠溃疡、慢性胃炎等。', '6', '2020-01-16 10:26:38', null);
INSERT INTO `drug` VALUES ('12', '消炎片', '290', '40.00', '功能主治 :清热解毒。用于上呼吸道感染的发热；支气管炎的咳嗽有痰及疖肿。', '7', '2020-01-16 10:28:52', null);
INSERT INTO `drug` VALUES ('13', '盐酸苯海拉明片', '350', '76.00', '【作用类别】本品为抗过敏及抗眩晕类非处方药药品。\r\n【适应症】用于皮肤黏膜的过敏，如荨麻疹、过敏性鼻炎、皮肤瘙痒症、药疹，对虫咬症和接触性皮炎也有效。亦可用于预防和治疗晕动病。', '3', '2020-01-16 10:30:14', null);
INSERT INTO `drug` VALUES ('14', '四季感冒片', '300', '59.00', '【适应症/主治功能】\r\n清热解毒，用于四季风寒感冒引起的发热头痛，鼻流清涕，咳嗽口干，咽喉疼痛，恶心厌食。', '4', '2020-01-16 10:31:37', null);
INSERT INTO `drug` VALUES ('15', '	复方苦参水杨酸散', '330', '67.00', '性状 ：本品为浅黄棕色至深棕红色粉末，久置有结成轻触即散的团块。\r\n适应症 ：用于手、足癣。', '4', '2020-01-16 10:33:04', null);
INSERT INTO `drug` VALUES ('16', '清肺化痰丸', '310', '48.00', '性状:本品为棕褐色至黑褐色的水蜜丸；味甜、苦、微麻。\r\n功能主治:降气化痰，止咳平喘。用于肺热咳嗽，痰多作喘，痰涎壅盛，肺气不畅。', '7', '2020-01-16 10:36:00', null);
INSERT INTO `drug` VALUES ('17', '活血止痛膏', '210', '65.00', '性状 :	本品为淡棕黄色至橙黄色的片状橡胶膏；气芳香。\r\n功能主治 :活血止痛，舒筋通络。用于筋骨疼痛，肌肉麻痹，痰核流注，关节酸痛。', '3', '2020-01-16 10:43:30', null);
INSERT INTO `drug` VALUES ('18', '麻仁润肠软胶囊', '350', '74.00', '性状 :	本品为软胶囊，内容物为深棕色粘稠状液体；气微香，味苦。\r\n功能主治 :润肠通便。用于肠燥便秘', '5', '2020-01-16 10:45:55', null);
INSERT INTO `drug` VALUES ('19', '麝香壮骨膏', '190', '71.00', '性状 :	本品为淡黄色至淡棕灰色的片状橡胶膏、气香。\r\n功能主治 :镇痛、消炎。本品用于风湿痛、关节痛、腰痛、神经痛、肌肉酸痛、扭伤、挫伤。', '10', '2020-01-16 10:48:05', null);
INSERT INTO `drug` VALUES ('20', '	盐酸氨基葡萄糖胶囊', '200', '89.00', '性状 :	本品为胶囊剂，内容物为白色或类白色粉末。\r\n适应症 :	用于治疗和预防全身所有部位的骨关节炎，包括膝关节、肩关节、髋关节、手腕关节、颈及脊椎关节和踝关节等。可缓解和消除骨关节炎的疼痛、肿胀等症状、改善关节活动功能。', '6', '2020-01-16 10:49:08', null);
INSERT INTO `drug` VALUES ('21', '	玄麦甘桔含片', '210', '68.00', '性状 :	本品为浅棕色至棕色的片；味甜，有清凉感。\r\n功能主治 :清热滋阴，祛痰利咽。用于阴虚火旺，虚火上浮，口鼻干燥，咽喉肿痛。', '1', '2020-01-16 10:51:25', null);
INSERT INTO `drug` VALUES ('22', '硝酸甘油片', '350', '88.00', '用法用量	片剂:成人一次用0.25~0.5mg(1片)舌下含服。每5分钟可重复1片,直至疼痛缓解。如果15分钟内总量达3片后疼痛持续存在,应立即就医。在活动或大便之前5~10分钟预防性使用,可避免诱发心绞痛。', '11', '2020-01-16 11:10:53', null);
INSERT INTO `drug` VALUES ('23', '	双花草珊瑚含片', '260', '55.00', '性状 :	本品为浅灰棕色的片；气香，味甜、略带辛凉。\r\n功能主治 :	疏风解热，利咽止痛。可改善咽喉干灼，疼痛，声音嘶哑。', '6', '2020-01-16 11:12:55', null);
INSERT INTO `drug` VALUES ('24', '抗骨质增生丸', '220', '70.00', '【性 状】\r\n本品为黑色的大蜜丸；味甘、微涩。\r\n【适应症/主治功能】\r\n补腰肾，强筋骨，活血，利气，止痛。用于增生性脊椎炎（肥大性胸椎、腰椎炎），颈椎综合症，骨刺等骨质增生症。', '8', '2020-01-16 11:14:41', null);
INSERT INTO `drug` VALUES ('25', '复方维生素U片', '260', '80.00', '功能主治	胃酸过多、胃灼热、胃部不舒适、胃部饱食感、胃胀、胸闷、打嗝(嗳气)、恶心(想呕吐、反胃、醉酒后恶心感等)、饮酒过多、胃痛、促进消化、消化不良、食欲缺乏、食物过量。', '5', '2020-01-16 11:16:31', null);
INSERT INTO `drug` VALUES ('26', '维生素B12片', '200', '70.00', '性状	本品为糖衣片，除去糖衣后显粉红色。\r\n适应症	用于巨幼红细胞性贫血。', '3', '2020-01-16 11:18:07', null);

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `history_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '病史编号',
  `history_information` varchar(255) DEFAULT NULL COMMENT '病史内容',
  `history_patient_id` int(11) DEFAULT NULL COMMENT '病人编号',
  `history_staff_id` int(11) DEFAULT NULL COMMENT '诊治医生编号',
  `history_createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '诊治时间',
  PRIMARY KEY (`history_id`),
  KEY `fk_h_p` (`history_patient_id`),
  KEY `fk_h_s` (`history_staff_id`),
  CONSTRAINT `fk_h_p` FOREIGN KEY (`history_patient_id`) REFERENCES `patient` (`patient_id`),
  CONSTRAINT `fk_h_s` FOREIGN KEY (`history_staff_id`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES ('3', '身体不适', '3', '2', '2020-01-17 14:29:27');

-- ----------------------------
-- Table structure for hospital
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital` (
  `hospital_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医院编号',
  `hostpital_information` text COMMENT '医院基本信息',
  `hostpital_telephone` varchar(255) DEFAULT NULL COMMENT '医院联系电话',
  `hostpital_createdate` datetime DEFAULT NULL COMMENT '医院建立时间',
  PRIMARY KEY (`hospital_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hospital
-- ----------------------------

-- ----------------------------
-- Table structure for hospitalization
-- ----------------------------
DROP TABLE IF EXISTS `hospitalization`;
CREATE TABLE `hospitalization` (
  `hospitalization_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '入院编号',
  `hospitalization_days` int(11) DEFAULT NULL COMMENT '住院天数',
  `hospitalization_patient_id` int(11) DEFAULT NULL COMMENT '住院病人编号',
  `hospitalization_staff_id` int(11) DEFAULT NULL COMMENT '治疗病人的住院医生编号',
  `hospitalization_cost` decimal(10,2) DEFAULT NULL COMMENT '总的住院费',
  `hospitalization_pay` decimal(10,2) DEFAULT NULL COMMENT '已缴纳费用',
  `hospitalization_rest` decimal(10,2) DEFAULT NULL COMMENT '仍未缴纳的费用',
  `hospitalization_cost_state` int(11) DEFAULT '0' COMMENT '是否缴纳所有住院费,0:未缴纳,1:已缴纳',
  `hospitalization_state` int(11) DEFAULT NULL COMMENT '是否出院,null:申请住院,0:未出院,1:已出院',
  `hospitalization_exitdate` datetime DEFAULT NULL COMMENT '出院时间',
  `hospitalization_room_id` int(11) DEFAULT NULL COMMENT '病人所在房间',
  `hospitalization_indate` datetime DEFAULT NULL COMMENT '病人入院时间',
  `hospitalization_reason` varchar(255) DEFAULT NULL COMMENT '病人申请住院的原因',
  `hospitalization_createdate` timestamp NULL DEFAULT NULL COMMENT 'CURRENT_TIMESTAMP',
  PRIMARY KEY (`hospitalization_id`),
  KEY `fk_hosp_p` (`hospitalization_patient_id`),
  KEY `fk_hosp_s` (`hospitalization_staff_id`),
  KEY `fk_hosp_r` (`hospitalization_room_id`),
  CONSTRAINT `fk_hosp_p` FOREIGN KEY (`hospitalization_patient_id`) REFERENCES `patient` (`patient_id`),
  CONSTRAINT `fk_hosp_r` FOREIGN KEY (`hospitalization_room_id`) REFERENCES `room` (`room_id`),
  CONSTRAINT `fk_hosp_s` FOREIGN KEY (`hospitalization_staff_id`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hospitalization
-- ----------------------------
INSERT INTO `hospitalization` VALUES ('4', '5', '3', '1', '1200.00', '200.00', '1000.00', '0', '0', null, '1', '2020-01-17 14:31:26', '身体不适', null);

-- ----------------------------
-- Table structure for income
-- ----------------------------
DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `income_id` int(11) NOT NULL AUTO_INCREMENT,
  `income_registration` decimal(10,2) DEFAULT NULL COMMENT '挂号收益',
  `income_work` decimal(10,2) DEFAULT NULL COMMENT '诊疗收益',
  `income_hospitalization` decimal(10,2) DEFAULT NULL COMMENT '住院收益',
  `income_prescription` decimal(10,2) DEFAULT NULL COMMENT '当天缴纳的药费',
  `income_total` decimal(10,2) DEFAULT NULL COMMENT '一天总收入',
  `income_createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`income_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of income
-- ----------------------------
INSERT INTO `income` VALUES ('1', '100.00', '100.00', '100.00', '100.00', '400.00', '2020-01-13 13:52:01');
INSERT INTO `income` VALUES ('2', '200.00', '200.00', '200.00', '200.00', '800.00', '2020-01-14 13:52:23');
INSERT INTO `income` VALUES ('3', '300.00', '300.00', '300.00', '300.00', '1200.00', '2020-01-15 13:52:38');
INSERT INTO `income` VALUES ('4', '400.00', '400.00', '400.00', '400.00', '1600.00', '2020-01-16 13:52:54');
INSERT INTO `income` VALUES ('5', '500.00', '500.00', '500.00', '500.00', '2000.00', '2020-01-17 13:53:07');
INSERT INTO `income` VALUES ('6', '600.00', '600.00', '600.00', '600.00', '2400.00', '2020-01-18 13:53:20');
INSERT INTO `income` VALUES ('7', '700.00', '700.00', '700.00', '700.00', '2800.00', '2020-01-19 13:53:30');

-- ----------------------------
-- Table structure for level
-- ----------------------------
DROP TABLE IF EXISTS `level`;
CREATE TABLE `level` (
  `level_id` int(11) NOT NULL,
  `level_name` varchar(255) DEFAULT NULL COMMENT '学历水平',
  PRIMARY KEY (`level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of level
-- ----------------------------

-- ----------------------------
-- Table structure for manufacturer
-- ----------------------------
DROP TABLE IF EXISTS `manufacturer`;
CREATE TABLE `manufacturer` (
  `manufacturer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '药物生产商编号',
  `manufacturer_name` varchar(255) DEFAULT NULL COMMENT '生产商品姓名',
  `manufacturer_telephone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`manufacturer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manufacturer
-- ----------------------------
INSERT INTO `manufacturer` VALUES ('1', '北京和谐药厂', '86-10-63311851');
INSERT INTO `manufacturer` VALUES ('2', '广州白云山陈李济药厂有限公司', '020-84218338');
INSERT INTO `manufacturer` VALUES ('3', '上海上药信谊药厂有限公司', '021-58995818');
INSERT INTO `manufacturer` VALUES ('4', '太极集团重庆桐君阁药厂有限公司', '023-88360226');
INSERT INTO `manufacturer` VALUES ('5', '浙江亚峰药厂有限公司', '0086-579-82271997');
INSERT INTO `manufacturer` VALUES ('6', '江西博莱大药厂有限公司', '0792-8193573');
INSERT INTO `manufacturer` VALUES ('7', '湖南华纳大药厂股份有限公司', '0731-85910565');
INSERT INTO `manufacturer` VALUES ('8', '荷本（北京）大药厂有限公司', '010-51731802 ');
INSERT INTO `manufacturer` VALUES ('9', '京都養生堂制药厂有限公司', '0-26920902');
INSERT INTO `manufacturer` VALUES ('10', '信谊药厂公司制药总厂', '02158540011');
INSERT INTO `manufacturer` VALUES ('11', '华北制药集团有限责任公司', '0311-85993999');
INSERT INTO `manufacturer` VALUES ('12', '常州制药厂有限公司', '0519-88813251');
INSERT INTO `manufacturer` VALUES ('13', '湖南方盛制药股份有限公司', '0731-88997188');
INSERT INTO `manufacturer` VALUES ('14', '长春普华制药股份有限公司', '0431-86902018');
INSERT INTO `manufacturer` VALUES ('15', '中国大冢制药有限公司', '022-23982883');
INSERT INTO `manufacturer` VALUES ('16', '苏州第四制药厂有限公司', '0512-52970922');
INSERT INTO `manufacturer` VALUES ('17', '广东邦民制药厂有限公司', '0750-3963166');
INSERT INTO `manufacturer` VALUES ('18', '银谷制药有限责任公司', '010-82525366');
INSERT INTO `manufacturer` VALUES ('19', '江西永昇制药股份有限公司', '0797-8378905');
INSERT INTO `manufacturer` VALUES ('20', '宁波立华制药有限公司', '0574-88046999');
INSERT INTO `manufacturer` VALUES ('21', '天津金耀集团河北永光制药有限公司', '010-61593867');
INSERT INTO `manufacturer` VALUES ('22', '海南制药厂有限公司', '0372-2905508');
INSERT INTO `manufacturer` VALUES ('23', '西安利君制药有限责任公司', '800-840-9198');
INSERT INTO `manufacturer` VALUES ('24', '四川科瑞德制药股份有限公司', '028-85550890');
INSERT INTO `manufacturer` VALUES ('25', '青岛黄海制药有限责任公司', '0532-88703686');
INSERT INTO `manufacturer` VALUES ('26', '北京东升制药有限公司', '010-80862323');
INSERT INTO `manufacturer` VALUES ('27', '易明医药-四川维奥制药有限公司', '028-86895099');
INSERT INTO `manufacturer` VALUES ('28', '江苏巨邦药业有限公司', '519-87301684');
INSERT INTO `manufacturer` VALUES ('29', '重庆康刻尔制药有限公司', '400-023-3868');
INSERT INTO `manufacturer` VALUES ('30', '红云制药（昆明）有限公司', '0871-65321429');
INSERT INTO `manufacturer` VALUES ('31', '上海金山制药有限公司', '021-57437165');
INSERT INTO `manufacturer` VALUES ('32', '山东新华制药股份有限公司', '0533-2196666');
INSERT INTO `manufacturer` VALUES ('33', '成都明日制药有限公司', '028-88432351');
INSERT INTO `manufacturer` VALUES ('34', '甘肃省西峰制药有限公司', '029-89520521');
INSERT INTO `manufacturer` VALUES ('35', '上海唯科生物制药有限公司', '021-62163616');
INSERT INTO `manufacturer` VALUES ('36', '山东华鲁制药有限公司', '0635--7129629');
INSERT INTO `manufacturer` VALUES ('37', '山东华辰制药有限公司', '0536-8881868');
INSERT INTO `manufacturer` VALUES ('38', '中科生物制药股份有限公司', '86-10-61597020');
INSERT INTO `manufacturer` VALUES ('39', '江苏聚荣制药集团有限公司', '0519-81238788');

-- ----------------------------
-- Table structure for nursing
-- ----------------------------
DROP TABLE IF EXISTS `nursing`;
CREATE TABLE `nursing` (
  `nursing_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '护士护理编号',
  `nursing_information` varchar(255) DEFAULT NULL COMMENT '护理内容',
  `nursing_patient_id` int(11) DEFAULT NULL COMMENT '病人编号',
  `nursing_createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '护理时间',
  `nursing_staff_id` int(11) DEFAULT NULL COMMENT '护士编号',
  PRIMARY KEY (`nursing_id`),
  KEY `fk_n_p` (`nursing_patient_id`),
  KEY `fk_n_s` (`nursing_staff_id`),
  CONSTRAINT `fk_n_p` FOREIGN KEY (`nursing_patient_id`) REFERENCES `patient` (`patient_id`),
  CONSTRAINT `fk_n_s` FOREIGN KEY (`nursing_staff_id`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nursing
-- ----------------------------
INSERT INTO `nursing` VALUES ('2', '打针', '3', '2020-01-17 14:32:09', '3');

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `patient_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '病人编号',
  `patient_code` varchar(255) DEFAULT NULL COMMENT '病人登录账号',
  `patient_password` varchar(255) DEFAULT NULL COMMENT '登录的密码',
  `patient_name` varchar(255) DEFAULT NULL COMMENT '病人姓名',
  `patient_sex` varchar(2) DEFAULT '男' COMMENT '病人性别',
  `patient_age` int(11) DEFAULT NULL COMMENT '病人年龄',
  `patient_telephone` varchar(255) DEFAULT NULL COMMENT '病人联系电话',
  `patient_identitycard` varchar(255) DEFAULT NULL COMMENT '病人身份证',
  `patient_department_id` int(255) DEFAULT NULL COMMENT '就诊科室编号',
  `patient_advice_id` int(11) DEFAULT NULL COMMENT '正在执行的医嘱',
  `patient_prescription_code` varchar(255) DEFAULT NULL COMMENT '病人要取的药方',
  `patient_hospitalization_id` int(11) DEFAULT NULL COMMENT '住院情况',
  `patient_createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`patient_id`),
  KEY `fk_patient_dp` (`patient_department_id`),
  KEY `fk_patient_ad` (`patient_advice_id`),
  KEY `fk_patient_pre` (`patient_prescription_code`),
  CONSTRAINT `fk_patient_ad` FOREIGN KEY (`patient_advice_id`) REFERENCES `advice` (`advice_id`),
  CONSTRAINT `fk_patient_dp` FOREIGN KEY (`patient_department_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `fk_patient_pre` FOREIGN KEY (`patient_prescription_code`) REFERENCES `prescription` (`prescription_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES ('2', 'patient', '123', '小张', '男', '20', '18200684453', '440883199911065039', null, null, null, null, '2020-01-17 14:25:22');
INSERT INTO `patient` VALUES ('3', 'usercode', '123', 'username', '男', '20', '18200684453', '440883199911065039', null, '4', '4674c206-9264-44f3-ac22-510b2234ec16', '4', '2020-01-17 14:28:01');

-- ----------------------------
-- Table structure for prescription
-- ----------------------------
DROP TABLE IF EXISTS `prescription`;
CREATE TABLE `prescription` (
  `prescription_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '药方主键',
  `prescription_code` varchar(255) NOT NULL COMMENT '药方编号(uuid生成),同一个药方编号相同',
  `prescription_drug_id` int(11) DEFAULT NULL COMMENT '药物编号',
  `prescription_drugnum` int(11) DEFAULT NULL COMMENT '药物数量',
  `prescription_totalprice` decimal(10,2) DEFAULT NULL COMMENT '该药物总价(数量x单价)',
  `prescription_patient_id` int(11) DEFAULT NULL COMMENT '病人编号',
  `prescription_state` int(11) DEFAULT '0' COMMENT '药方状态',
  `prescription_modifydate` datetime DEFAULT NULL COMMENT '缴纳费用的时间',
  `prescription_createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`prescription_id`,`prescription_code`),
  KEY `prescription_code` (`prescription_code`),
  KEY `fk_pre_dg` (`prescription_drug_id`),
  KEY `fk_pre_patient` (`prescription_patient_id`),
  CONSTRAINT `fk_pre_dg` FOREIGN KEY (`prescription_drug_id`) REFERENCES `drug` (`drug_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prescription
-- ----------------------------
INSERT INTO `prescription` VALUES ('1', 'fa159d0e-aaac-4cba-b3cb-f9a07aba489a', '1', '2', '106.00', '1', '0', null, '2020-01-16 11:28:49');
INSERT INTO `prescription` VALUES ('2', 'fa159d0e-aaac-4cba-b3cb-f9a07aba489a', '17', '3', '195.00', '1', '0', null, '2020-01-16 11:28:49');
INSERT INTO `prescription` VALUES ('3', 'ee70a367-a916-46a1-8ab6-98b0e71c7185', '1', '1', '53.00', '1', '0', null, '2020-01-16 12:06:49');
INSERT INTO `prescription` VALUES ('4', 'f5ec322b-55e9-4f59-875e-2f20608a6ca2', '1', '2', '106.00', '1', '0', null, '2020-01-16 12:09:33');
INSERT INTO `prescription` VALUES ('5', 'a13b01e4-2e93-4956-9fe2-04e22cd744bb', '1', '1', '53.00', '1', '0', null, '2020-01-16 12:11:06');
INSERT INTO `prescription` VALUES ('6', 'f0ad70e1-efd3-481e-9822-6f31031132f9', '1', '2', '106.00', '1', '0', '2020-01-16 13:41:07', '2020-01-16 13:38:35');
INSERT INTO `prescription` VALUES ('7', '4674c206-9264-44f3-ac22-510b2234ec16', '2', '2', '46.00', '3', '1', '2020-01-17 14:29:56', '2020-01-17 14:29:44');
INSERT INTO `prescription` VALUES ('8', '4674c206-9264-44f3-ac22-510b2234ec16', '1', '1', '53.00', '3', '1', '2020-01-17 14:29:56', '2020-01-17 14:29:45');

-- ----------------------------
-- Table structure for queue
-- ----------------------------
DROP TABLE IF EXISTS `queue`;
CREATE TABLE `queue` (
  `queue_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '挂号队列编号',
  `queue_registration_id` int(11) DEFAULT NULL COMMENT '当前就诊的挂号',
  `queue_department_id` int(11) DEFAULT NULL COMMENT '科室编号',
  PRIMARY KEY (`queue_id`),
  KEY `fk_q_dp` (`queue_department_id`),
  KEY `fk_q_rg` (`queue_registration_id`),
  CONSTRAINT `fk_q_dp` FOREIGN KEY (`queue_department_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `fk_q_rg` FOREIGN KEY (`queue_registration_id`) REFERENCES `registration` (`registration_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of queue
-- ----------------------------
INSERT INTO `queue` VALUES ('5', '4', '1');
INSERT INTO `queue` VALUES ('6', '5', '4');

-- ----------------------------
-- Table structure for registration
-- ----------------------------
DROP TABLE IF EXISTS `registration`;
CREATE TABLE `registration` (
  `registration_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '挂号编号',
  `registration_patient_id` int(11) DEFAULT NULL COMMENT '挂号的病人编号',
  `registration_department_id` int(11) DEFAULT NULL COMMENT '挂号科室',
  `registration_staff_id` int(11) DEFAULT NULL COMMENT '诊治医生',
  `registration_createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '挂号时间',
  `registration_state` int(11) DEFAULT '0' COMMENT '挂号状态(0:未处理,1:已处理,2:退号)',
  `registration_cost` decimal(10,0) DEFAULT NULL COMMENT '挂号费',
  PRIMARY KEY (`registration_id`),
  KEY `fk_rg_patient` (`registration_patient_id`),
  KEY `fk_rg_dp` (`registration_department_id`),
  KEY `fk_rg_st` (`registration_staff_id`),
  CONSTRAINT `fk_rg_dp` FOREIGN KEY (`registration_department_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `fk_rg_patient` FOREIGN KEY (`registration_patient_id`) REFERENCES `patient` (`patient_id`),
  CONSTRAINT `fk_rg_st` FOREIGN KEY (`registration_staff_id`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of registration
-- ----------------------------
INSERT INTO `registration` VALUES ('4', '2', '1', null, '2020-01-17 14:25:51', '0', '50');
INSERT INTO `registration` VALUES ('5', '3', '4', null, '2020-01-17 14:28:27', '0', '90');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '职位编号',
  `role_name` varchar(255) DEFAULT NULL COMMENT '职位名称',
  `role_number` int(11) DEFAULT NULL COMMENT '该职位人数',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '院长', '1');
INSERT INTO `role` VALUES ('2', '门诊医生', '1');
INSERT INTO `role` VALUES ('3', '住院医生', '1');
INSERT INTO `role` VALUES ('4', '护士', '1');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '医院房间编号',
  `room_name` varchar(255) DEFAULT NULL COMMENT '房间名',
  `room_department_id` int(11) DEFAULT NULL COMMENT '对应科室编号',
  `room_restnum` int(11) DEFAULT NULL COMMENT '剩余床位数',
  `room_usenum` int(11) DEFAULT NULL COMMENT '已使用床位数',
  `room_totalnum` int(11) DEFAULT NULL COMMENT '总的床位数',
  PRIMARY KEY (`room_id`),
  KEY `fk_r_dp` (`room_department_id`),
  CONSTRAINT `fk_r_dp` FOREIGN KEY (`room_department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1', '呼吸室', '4', '3', '7', '10');

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
  `salary_id` int(11) NOT NULL AUTO_INCREMENT,
  `salary_staff_id` int(11) DEFAULT NULL COMMENT '员工编号',
  `salary_pay` decimal(10,2) DEFAULT NULL COMMENT '实发工资[(月薪/当月天数)*出勤天数](jdk8计算当月天数)',
  `salary_month` decimal(10,2) DEFAULT NULL COMMENT '一个月的工资',
  PRIMARY KEY (`salary_id`),
  KEY `fk_sal_staff` (`salary_staff_id`),
  CONSTRAINT `fk_sal_staff` FOREIGN KEY (`salary_staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salary
-- ----------------------------
INSERT INTO `salary` VALUES ('1', '4', '129.03', '2000.00');
INSERT INTO `salary` VALUES ('2', '3', '96.77', '3000.00');
INSERT INTO `salary` VALUES ('3', '1', '129.03', '4000.00');
INSERT INTO `salary` VALUES ('4', '2', '161.29', '5000.00');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `staff_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '职工编号',
  `staff_code` varchar(255) DEFAULT NULL COMMENT '职工工号(用于登录)',
  `staff_password` varchar(255) DEFAULT NULL COMMENT '登录的密码',
  `staff_name` varchar(255) DEFAULT NULL COMMENT '职工姓名',
  `staff_sex` varchar(2) DEFAULT NULL COMMENT '职工性别',
  `staff_age` int(11) DEFAULT NULL COMMENT '职工年龄',
  `staff_telephone` varchar(255) DEFAULT NULL COMMENT '职工电话',
  `staff_days` int(11) DEFAULT NULL COMMENT '签到天数',
  `staff_department_id` int(11) DEFAULT NULL COMMENT '所属科室',
  `staff_role_id` int(11) DEFAULT NULL COMMENT '职位',
  `staff_salary_id` int(11) DEFAULT NULL COMMENT '职工薪水(实发工资由签到情况决定)',
  `staff_attendance_id` int(11) DEFAULT NULL COMMENT '出勤情况',
  `staff_work_id` int(11) DEFAULT NULL COMMENT '工作情况',
  `staff_modifydate` datetime DEFAULT NULL COMMENT '修改时间',
  `staff_createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `staff_level_id` int(11) DEFAULT NULL COMMENT '文化水平',
  `staff_cost` decimal(10,0) DEFAULT NULL COMMENT '医生诊疗费',
  `staff_state` int(11) DEFAULT NULL COMMENT '是否在职(0:离职,1:在职)',
  PRIMARY KEY (`staff_id`),
  KEY `fk_s_a` (`staff_attendance_id`),
  KEY `fk_s_d` (`staff_department_id`),
  KEY `fk_s_l` (`staff_level_id`),
  KEY `fk_s_r` (`staff_role_id`),
  CONSTRAINT `fk_s_a` FOREIGN KEY (`staff_attendance_id`) REFERENCES `attendance` (`attendance_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_s_d` FOREIGN KEY (`staff_department_id`) REFERENCES `department` (`department_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_s_l` FOREIGN KEY (`staff_level_id`) REFERENCES `level` (`level_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_s_r` FOREIGN KEY (`staff_role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('1', 'ind', '123', '住院医生', '男', '25', '1516163864', '1', '4', '3', null, null, null, null, '2020-01-16 10:50:14', null, '200', null);
INSERT INTO `staff` VALUES ('2', 'outd', '123', '门诊医生', '男', '26', '1564785653', '1', '4', '2', null, null, null, null, '2020-01-16 11:10:04', null, '100', null);
INSERT INTO `staff` VALUES ('3', 'nurse', '123', '护士', '女', '20', '1546465789', '1', '4', '4', null, null, null, null, '2020-01-16 13:44:37', null, '100', null);
INSERT INTO `staff` VALUES ('4', 'master', '123', '院长', '男', '30', '1566549879', '2', '4', '1', null, null, null, null, '2020-01-16 13:48:50', null, '1000', null);

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work` (
  `work_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工作情况编号(工作量等)',
  `work_num` int(11) DEFAULT NULL COMMENT '诊治人数',
  `work_staff_id` int(11) DEFAULT NULL COMMENT '医生编号',
  `work_income` decimal(10,0) DEFAULT NULL COMMENT '诊疗收入',
  `work_createdate` datetime DEFAULT NULL COMMENT '当日工作时间',
  PRIMARY KEY (`work_id`),
  KEY `fk_w_s` (`work_staff_id`),
  CONSTRAINT `fk_w_s` FOREIGN KEY (`work_staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work
-- ----------------------------
INSERT INTO `work` VALUES ('1', '1', '2', '100', null);
INSERT INTO `work` VALUES ('2', '1', '2', '100', null);
