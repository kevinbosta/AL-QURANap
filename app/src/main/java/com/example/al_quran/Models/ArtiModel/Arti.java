package com.example.al_quran.Models.ArtiModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Arti{

	@SerializedName("translations")
	private List<TranslationsItem> translations;

	public List<TranslationsItem> getTranslations(){
		return translations;
	}

	@Override
 	public String toString(){
		return 
			"Arti{" + 
			"translations = '" + translations + '\'' + 
			"}";
		}
}