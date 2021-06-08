# Sypter
Small library to double encrypt (AES + RSA) data and generate digital signature.

Spring XML configuration may look like this:

	<x:sypter id="" certificate="PATH_TO_CERT"
		keystore="PATH_TO_KEYSTORE" keystore-password="KEYSTORE_PASSWORD"
		key-alias="KEY_ALIAS" key-password="KEY_PASSWORD" />

An example can be checked in <i>src/test/resources/spring</i>