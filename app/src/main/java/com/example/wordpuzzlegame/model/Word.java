package com.example.wordpuzzlegame.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Word {

    private long id;
    private String word;
    private long word_code;
    private long lang_id;
    private byte[] image;
    private String langName;

    private Context context;
    private DataSource dataSource;

    public Word() {
    }

    public Word(Context context) {
        dataSource = new DataSource(context);
        this.context = context;
    }

    public Word(String word, long word_code, long lang_id) {
        this.word = word;
        this.word_code = word_code;
        this.lang_id = lang_id;
    }

    public Word(long id, String word, long word_code, long lang_id) {
        this.id = id;
        this.word = word;
        this.word_code = word_code;
        this.lang_id = lang_id;
    }

    public Word(long id, String word, long word_code, long lang_id, byte[] image) {
        this.id = id;
        this.word = word;
        this.word_code = word_code;
        this.lang_id = lang_id;
        this.image = image;
    }

    public Word(long id, String word, long word_code, long lang_id, byte[] image, String langName) {
        this.id = id;
        this.word = word;
        this.word_code = word_code;
        this.lang_id = lang_id;
        this.image = image;
        this.langName = langName;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getWord_code() {
        return word_code;
    }

    public void setWord_code(long word_code) {
        this.word_code = word_code;
    }

    public long getLang_id() {
        return lang_id;
    }

    public void setLang_id(long lang_id) {
        this.lang_id = lang_id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public long insertWords(ArrayList<Word> words) {

        ContentValues contentValues = null;
        long result;
        for (Word word :
                words) {
            contentValues = new ContentValues();
            contentValues.put(ItemTables.WORD, word.getWord());
            contentValues.put(ItemTables.WORD_CODE, word.getWord_code());
            contentValues.put(ItemTables.LANG_ID, word.getLang_id());
            result = dataSource.createItem(contentValues, ItemTables.WORD_TABLE);
            if(result == -1) {
                return result;
            }
//            contentValues.put(ItemTables.WORD, );
        }
        return 1;

//        return dataSource.createItem(contentValues, ItemTables.WORD_TABLE);

    }

    public ArrayList<Word> wordArrayList(long langId, boolean pictureIncluded) {

        Cursor words = dataSource.words(langId, ItemTables.LANG_ID, ItemTables.WORD_TABLE);
//        Cursor assets = dataSource.getAll(ItemTables.ASSETS_TABLE);

        ArrayList<Word> wordArrayList = new ArrayList<>();

        Assets assets = new Assets(context);
        words.moveToFirst();
//        assets.moveToFirst();
//        assets.moveToPosition(words.getInt(words.getColumnIndex("word_code")));

        while(!words.isAfterLast()) {

            long id = words.getLong(words.getColumnIndex(ItemTables.WORD_ID));
            String word = words.getString(words.getColumnIndex(ItemTables.WORD));
            long word_code = words.getLong(words.getColumnIndex(ItemTables.WORD_CODE));
            Assets singleAsset = assets.singleAsset((int) word_code);
//            assets.moveToPosition((int) word_code);
//            byte[] image = assets.getBlob(assets.getColumnIndex(ItemTables.IMAGE));
            byte[] image = singleAsset.getImage();
            long lang_id = words.getLong(words.getColumnIndex(ItemTables.LANG_ID));

            Word singleWord;

            if(pictureIncluded) {

                singleWord = new Word(id, word, word_code, lang_id, image);

            } else {

                singleWord = new Word(id, word, word_code, lang_id);

            }
            wordArrayList.add(singleWord);
//            assets.moveToPosition((int) word_code);
            words.moveToNext();

        }

        return wordArrayList;

    }

    public ArrayList<Word> prepareForGame(long langId, boolean pictureIncluded, int wordLength) {

        Cursor words = dataSource.words(langId, ItemTables.LANG_ID, ItemTables.WORD_TABLE);
//        Cursor assets = dataSource.getAll(ItemTables.ASSETS_TABLE);

        ArrayList<Word> wordArrayList = new ArrayList<>();

        Assets assets = new Assets(context);
        words.moveToFirst();
//        assets.moveToFirst();
//        assets.moveToPosition(words.getInt(words.getColumnIndex("word_code")));

        while(!words.isAfterLast()) {

            long id = words.getLong(words.getColumnIndex(ItemTables.WORD_ID));
            String word = words.getString(words.getColumnIndex(ItemTables.WORD));
            long word_code = words.getLong(words.getColumnIndex(ItemTables.WORD_CODE));
            Assets singleAsset = assets.singleAsset((int) word_code);
//            assets.moveToPosition((int) word_code);
//            byte[] image = assets.getBlob(assets.getColumnIndex(ItemTables.IMAGE));
            byte[] image = singleAsset.getImage();
            long lang_id = words.getLong(words.getColumnIndex(ItemTables.LANG_ID));

            Word singleWord;

            if(word.length() > wordLength) {
                if (pictureIncluded) {

                    singleWord = new Word(id, word, word_code, lang_id, image);

                } else {

                    singleWord = new Word(id, word, word_code, lang_id);

                }
                wordArrayList.add(singleWord);
            }
//            assets.moveToPosition((int) word_code);
            words.moveToNext();

        }

        return wordArrayList;


    }

    public long getCurrentWordCode() {

        Cursor cursor = dataSource.getAll(ItemTables.WORD_TABLE);

        cursor.moveToLast();

        return cursor.getLong(cursor.getColumnIndex(ItemTables.WORD_CODE));

    }

    public int deleteWords(long word_code) {

        return dataSource.deleteByLong(word_code, ItemTables.KID_TABLE, ItemTables.WORD_CODE);

    }

    public ArrayList<Word> getByWordCode(long wordCode) {

        ArrayList<Word> words = new ArrayList<>();

        Cursor wordCursor = dataSource.items(wordCode, ItemTables.WORD_CODE, ItemTables.WORD_TABLE);

        wordCursor.moveToFirst();

        while(!wordCursor.isAfterLast()) {
            long wordId = wordCursor.getLong(wordCursor.getColumnIndex(ItemTables.WORD_ID));
            long langId = wordCursor.getLong(wordCursor.getColumnIndex(ItemTables.LANG_ID));
            String wordName = wordCursor.getString(wordCursor.getColumnIndex(ItemTables.WORD));
            Cursor langCursor = dataSource.items(langId, ItemTables.LANG_ID, ItemTables.LANGUAGE_TABLE);
            langCursor.moveToFirst();
            String langName = langCursor.getString(langCursor.getColumnIndex(ItemTables.LANGUAGE));
            Cursor assetCursor = dataSource.items(wordCode, ItemTables.WORD_CODE, ItemTables.ASSETS_TABLE);
            assetCursor.moveToFirst();
            byte[] image = assetCursor.getBlob(assetCursor.getColumnIndex(ItemTables.IMAGE));

            Word word = new Word(wordId, wordName, wordCode, langId, image, langName);
            words.add(word);
            wordCursor.moveToNext();
            langCursor.close();
            assetCursor.close();
        }

        return words;
    }
    //    private long id;
//    private String word;
//    private String word_definition;
//    private String word_example;
//    private String word_timestamp;
//    private byte[] image;
//    private String language;
//    private DataSource dataSource;
//    private Context context;
//
//    public Word(long id, String word, byte[] image) {
//        this.id = id;
//        this.word = word;
//        this.image = image;
//    }
//    public Word(String word, String word_example, String language, byte[] image) {
//
//        this.word = word;
//        this.word_example = word_example;
//        this.language = language;
//        this.image = image;
//
//    }
//
//    public Word(String word, String word_definition, String word_example, Context context) {
//
//        this.word = word;
//        this.word_definition = word_definition;
//        this.word_example = word_example;
//        this.context = context;
//        dataSource = new DataSource(context);
//
//    }
//
////    public Word(String word, String word_definition, String word_example) {
////
////        this.word = word;
////        this.word_definition = word_definition;
////        this.word_example = word_example;
////
////    }
//
//    public Word(Context context) {
//
//        this.context = context;
//        dataSource = new DataSource(context);
//
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getWord() {
//        return word;
//    }
//
//    public void setWord(String word) {
//        this.word = word;
//    }
//
//    public String getWord_definition() {
//        return word_definition;
//    }
//
//    public void setWord_definition(String word_definition) {
//        this.word_definition = word_definition;
//    }
//
//    public String getWord_example() {
//        return word_example;
//    }
//
//    public void setWord_example(String word_example) {
//        this.word_example = word_example;
//    }
//
//    public String getWord_timestamp() {
//        return word_timestamp;
//    }
//
//    public void setWord_timestamp(String word_timestamp) {
//        this.word_timestamp = word_timestamp;
//    }
//
//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }
//
//    public String getLanguage() {
//        return language;
//    }
//
//    public void setLanguage(String language) {
//        this.language = language;
//    }
//
//    public ArrayList<Word> wordArrayList(String lang) {
//
//        Cursor wordsCursor = dataSource.getAllElements(lang);
//        Cursor relationshipCursor = dataSource.getAll();
//
//        ArrayList<Word> words = new ArrayList<>();
//
//        wordsCursor.moveToFirst();
//        relationshipCursor.moveToFirst();
//
//        while(!wordsCursor.isAfterLast()) {
//
////            long id = relationshipCursor.getLong(relationshipCursor.getColumnIndex(ItemTables.RELATIONSHIP_ID));
//            String word = wordsCursor.getString(1);
//
//            byte[] image = relationshipCursor.getBlob(relationshipCursor.getColumnIndex(ItemTables.IMAGE));
////            String word_definition = wordsCursor.getString(2);
////            String word_example = wordsCursor.getString(2);
//
//            Word word1 = new Word(id, word, image);
//            words.add(word1);
//            wordsCursor.moveToNext();
//            relationshipCursor.moveToNext();
//        }
//
//        wordsCursor.close();
//
//        return words;
//
//    }
//
//    public void saveWords(ArrayList<Word> words) {
//
//        ArrayList<Long> ids = new ArrayList<>();
//        ArrayList<String> tableNames = new ArrayList<>();
////        tableNames.add(ItemTables.ENGLISH_WORD);
////        tableNames.add(ItemTables.ENGLISH_EXAMPLE);
////        tableNames.add(ItemTables.TABLE_ENGLISH);
////        tableNames.add(ItemTables.AMHARIC_WORD);
////        tableNames.add(ItemTables.AMHARIC_EXAMPLE);
////        tableNames.add(ItemTables.TABLE_AMHARIC);
////        tableNames.add(ItemTables.TIGIRIGNA_WORD);
////        tableNames.add(ItemTables.TIGIRIGNA_EXAMPLE);
////        tableNames.add(ItemTables.TABLE_TIGIRIGNA);
//
//        int tableIndex = 0;
//        for (int i = 0; i < words.size(); i++) {
//
//            Word word = words.get(i);
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(tableNames.get(tableIndex), word.getWord());
//            tableIndex++;
//            contentValues.put(tableNames.get(tableIndex), word.getWord_example());
//            tableIndex++;
//
//            long id = dataSource.createItem(contentValues, tableNames.get(tableIndex));
//            Log.i(tableNames.get(tableIndex), String.valueOf(id));
//            ids.add(id);
//            tableIndex++;
//
//
//        }

//        ContentValues contentValues = new ContentValues();
//        contentValues.put(ItemTables.ID_ENGLISH, ids.get(0));
//        contentValues.put(ItemTables.ID_AMHARIC, ids.get(1));
//        contentValues.put(ItemTables.ID_TIGIRIGNA, ids.get(2));
//        long id = dataSource.createItem(contentValues, ItemTables.TABLE_RELATIONSHIP);
//        Log.i(ItemTables.TABLE_RELATIONSHIP, String.valueOf(id));

//    }

//    public ArrayList<Word> allListed() {
//
//        Cursor relationship = dataSource.getAll();
//        Cursor englishWords = dataSource.getAllElements("ENGLISH");
//        Cursor amharicWords = dataSource.getAllElements("AMHARIC");
//        Cursor tigrignaWords = dataSource.getAllElements("TIGRIGNA");
//
//        ArrayList<Word> words = new ArrayList<>();
//
//        relationship.moveToFirst();
//        englishWords.moveToFirst();
//        amharicWords.moveToFirst();
//        tigrignaWords.moveToFirst();
//
//        while(!relationship.isAfterLast()) {
//
//            String word = englishWords
//
//        }
//
//    }
}
