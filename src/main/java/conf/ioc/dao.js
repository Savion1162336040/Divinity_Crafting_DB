var ioc = {
        dataSource : {
            type : "com.alibaba.druid.pool.DruidDataSource",
            events : {
                create : "init",
                depose : 'close'
            },
            fields : {
            	//serverTimezone:服务器时区UTC(标准时间，原子时间UTC=GTM+0),GTM(格林威治时间GTM=UTC+0),CST(北京时间:CST=UTC+8),PST(太平洋时间:PST=UTC-8)
            	//useSSL:是否使用SSL
            	//userUnicode:是否是使用Unicode编码
            	//characterEncoding:编码格式
                url : "jdbc:mysql://localhost:3306/divinity?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8",
                username : "savion",
                password : "sw820820",
                testWhileIdle : true, // 非常重要,预防mysql的8小时timeout问题
                //validationQuery : "select 1" , // Oracle的话需要改成 select 1 from dual
                maxActive : 100
            }
        },
        dao : {
            type : "org.nutz.dao.impl.NutDao",
            args : [{refer:"dataSource"}]
        }
};