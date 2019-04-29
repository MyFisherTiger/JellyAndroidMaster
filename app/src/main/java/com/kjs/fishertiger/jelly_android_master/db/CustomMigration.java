package com.kjs.fishertiger.jelly_android_master.db;
import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;


public class CustomMigration implements RealmMigration {
	@Override
	public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
		RealmSchema schema = realm.getSchema();
		if (oldVersion == 0 && newVersion == 1) {
			RealmObjectSchema personSchema = schema.get("User");
			personSchema
					.addField("age", int.class);
			oldVersion++;
		}else if(oldVersion == 1&&newVersion==2){
			RealmObjectSchema personSchema = schema.get("User");
			personSchema
					.addField("address", String.class);
			oldVersion++;
		}
	}
}
