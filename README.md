# hsf-demo-1
Simple demo project with multi-modules based on the Alibaba HSF

## Preparation

1. Install Maven

**Note** edas repository should be added into your Maven settings under ~/.m2/settings.xml (see docs/settings.xml) 

2. Install Git

## Quick Start

1. Clone source code into local environment

	1). Clone using git protocol  
	2). Clone using https protocol

2. Import project as Maven project into your IDE 

	Either Eclipse or IDEA is workable
	
3. Run the project

	1). Download and start EDAS light-weighted Config Center
	
	The introduction of light-weighted Config Center is [here](https://help.aliyun.com/document_detail/44163.html?spm=a2c4g.11186623.2.18.19316f3eufiR2H)
	
	The light-weighted Config Center downloaded URL is [here](https://edas-public.oss-cn-hangzhou.aliyuncs.com/install_package/LCC/2018-11-27/edas-lite-configcenter.20181127.tar.gz?spm=a2c4g.11186623.2.15.d5445e31HGsW5v&file=edas-lite-configcenter.20181127.tar.gz)
	
	The advanced features can be found [here](https://yq.aliyun.com/articles/673137?spm=a2c4e.11155472.0.0.1ea81dabEyGaGp)
	
	You can untar the archived file and run from startup.sh, and you can also run like below if startup.sh doesn't work.
	
	~~~
	java -jar /opt/edas-lite-configcenter/lib/edas-config-center.jar
	~~~
	
	/etc/hosts
	
	~~~
	127.0.0.1 jmenv.tbsite.net
	~~~
	
	**Note that** the config service is listening on 8080 by default.

	2). You can run the provider and consumer project in either of the following ways.
	
	* 	Using spring-boot Run CLI
	
		a) cd hsf-demo-1-provider  
		b) mvn clean spring-boot:run [-Dspring.profiles.active=<profile>]  
		c) cd hsf-demo-1-consumer  
		d) mvn clean spring-boot:run [-Dspring.profiles.active=<profile>] 
		
	*   Using fat jar CLI
	
		a) cd hsf-demo-1-provider  
		b) mvn clean package  
		c) java -jar target/hsf-demo-1-provider-0.0.1-SNAPSHOT.jar  
		d) cd hsf-demo-1-consumer  
		e) mvn clean package  
		f) java -jar target/hsf-demo-1-consumer-0.0.1-SNAPSHOT.jar  
	
	*   Start from Eclipse
	
		a) Run As -> Maven install  
		b) Run As -> Maven build ... ->  
			Goal: clean spring-boot:run  
			Proile: < profile >  
			-> VM: -Dvipserver.server.port=8080
			
## Create your own HSF demo project

1. New one Maven Project with plain Java support as parent project
	
	pom.xml
		
	~~~
	<project>
	
		<modelVersion>4.0.0</modelVersion>
		<groupId>net.yarn</groupId>
		<artifactId>hsf-demo-1</artifactId>
		<version>0.0.1-SNAPSHOT</version>
		<packaging>pom</packaging>
	
		<packaging>pom</packaging>
		<modules>
			<module>hsf-demo-1-api</module>
			<module>hsf-demo-1-provider</module>
			<module>hsf-demo-1-consumer</module>
		</modules>
		
		<properties>
			<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
			<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
			<java.version>1.8</java.version>
			<swagger.version>2.9.2</swagger.version>
			<modelmapper.version>2.1.0</modelmapper.version>
			<javatuples.version>1.2</javatuples.version>
			<activiti.version>5.22.0</activiti.version>
			<groovy.version>2.4.15</groovy.version>
			<javatuples.version>1.2</javatuples.version>
			<spring-boot.version>2.0.1.RELEASE</spring-boot.version>
			<spring-cloud.version>Finchley.SR2</spring-cloud.version>
			<optimus-common.version>1.1.3-SNAPSHOT</optimus-common.version>
			<vipclient.version>1.3</vipclient.version>
			<hsf.version>1.3</hsf.version>
			<pandora.version>1.3</pandora.version>
			<xmlgraphics.version>1.7</xmlgraphics.version>
			<nekohtml.version>1.9.22</nekohtml.version>
			<commons-io.version>2.4</commons-io.version>
			<commons-fileupload.version>1.2.2</commons-fileupload.version>
			<spring-boot-mybatis.version>1.3.2</spring-boot-mybatis.version>
		</properties>
	
		<dependencies>
			<dependency>
				<groupId>org.slf4j</groupId>
				<artifactId>slf4j-log4j12</artifactId>
				<version>1.5.8</version>
			</dependency>
			<dependency>
				<groupId>org.projectlombok</groupId>
				<artifactId>lombok</artifactId>
				<version>1.16.18</version>
			</dependency>
		</dependencies>
	
		<dependencyManagement>
			<dependencies>
				<dependency>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-dependencies</artifactId>
					<version>${spring-boot.version}</version>
					<type>pom</type>
					<scope>import</scope>
				</dependency>
				<dependency>
					<groupId>org.springframework.cloud</groupId>
					<artifactId>spring-cloud-dependencies</artifactId>
					<version>${spring-cloud.version}</version>
					<type>pom</type>
					<scope>import</scope>
				</dependency>
				
				<!-- Begin HSF -->
				<dependency>
					<groupId>org.springframework.cloud</groupId>
					<artifactId>spring-cloud-starter-hsf</artifactId>
					<version>${hsf.version}</version>
				</dependency>
				<dependency>
					<groupId>org.springframework.cloud</groupId>
					<artifactId>spring-cloud-starter-pandora</artifactId>
					<version>${pandora.version}</version>
				</dependency>
				<!-- End HSF -->
				
				<!-- Begin Swagger -->
				<dependency>
					<groupId>io.springfox</groupId>
					<artifactId>springfox-swagger2</artifactId>
					<version>${swagger.version}</version>
				</dependency>
				<dependency>
					<groupId>io.springfox</groupId>
					<artifactId>springfox-swagger-ui</artifactId>
					<version>${swagger.version}</version>
				</dependency>
				<!-- End Swagger -->
				
			</dependencies>
		</dependencyManagement>
		
		<build>
			<plugins>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-compiler-plugin</artifactId>
					<version>3.7.0</version>
					<configuration>
						<source>1.8</source>
						<target>1.8</target>
					</configuration>
				</plugin>
			</plugins>
		</build>
	</project>
	~~~
	
2. Create one module with plain Java support as API project

3. Create one module with Spring boot and HSF support as provider project
	
	3.1) Generate POM using https://start.spring.io/  
	3.2) Manually update parent pom.xml to add it as module if necessary
	
	pom.xml
	
	~~~
	<project>
	
		<parent>
			<groupId>net.yarn</groupId>
			<artifactId>hsf-demo-1</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</parent>
		
		
		
		<dependencies>
			<dependency>
				<groupId>net.yarn</groupId>
				<artifactId>hsf-demo-1-api</artifactId>
				<version>0.0.1-SNAPSHOT</version>
			</dependency>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-starter-hsf</artifactId>
			</dependency>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-starter-pandora</artifactId>
			</dependency>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-web</artifactId>
			</dependency>
			<dependency>
				<groupId>io.springfox</groupId>
				<artifactId>springfox-swagger2</artifactId>
			</dependency>
			<dependency>
				<groupId>io.springfox</groupId>
				<artifactId>springfox-swagger-ui</artifactId>
			</dependency>
		</dependencies>
	
		<build>
			<plugins>
				<plugin>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-maven-plugin</artifactId>
					<executions>
						<execution>
							<goals>
								<goal>repackage</goal>
							</goals>
						</execution>
					</executions>
				</plugin>
			</plugins>
		</build>
	</project>
	~~~
	
4. Create one module with Spring boot web and HSF support as consumer project
	
	4.1) Generate POM using https://start.spring.io/  
	4.2) Manually update parent pom.xml to add it as module if necessary
	   
	pom.xml
	
	~~~
	<project>
	
		<parent>
			<groupId>net.yarn</groupId>
			<artifactId>hsf-demo-1</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</parent>
		
		
		
		<dependencies>
			<dependency>
				<groupId>net.yarn</groupId>
				<artifactId>hsf-demo-1-api</artifactId>
				<version>0.0.1-SNAPSHOT</version>
			</dependency>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-starter-hsf</artifactId>
			</dependency>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-starter-pandora</artifactId>
			</dependency>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-web</artifactId>
			</dependency>
			<dependency>
				<groupId>io.springfox</groupId>
				<artifactId>springfox-swagger2</artifactId>
			</dependency>
			<dependency>
				<groupId>io.springfox</groupId>
				<artifactId>springfox-swagger-ui</artifactId>
			</dependency>
		</dependencies>
	
		<build>
			<plugins>
				<plugin>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-maven-plugin</artifactId>
					<executions>
						<execution>
							<goals>
								<goal>repackage</goal>
							</goals>
						</execution>
					</executions>
				</plugin>
			</plugins>
		</build>
	</project>
	~~~
	
5. Install API dependency locally
	
	Select API module 
	*   Run As -> Maven install
	*   mvn clean install
	
6. Sync Eclipse classpath or project settings with POM

	Maven -> Update Project
