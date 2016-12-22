encryption property values:
java -cp .m2/repository/org/jasypt/jasypt/1.9.2/jasypt-1.9.2.jar  org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="val" password=pwd algorithm=PBEWithMD5AndDES
VM options:
-Djasypt.encryptor.password=pwd