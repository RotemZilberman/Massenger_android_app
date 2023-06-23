package com.example.whatsapp_application.room;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.whatsapp_application.entities.Chat;
import com.example.whatsapp_application.entities.Message;
import com.example.whatsapp_application.entities.User;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ChatDao_Impl implements ChatDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Chat> __insertionAdapterOfChat;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public ChatDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfChat = new EntityInsertionAdapter<Chat>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `chats` (`PrimaryKey`,`id`,`user`,`lastMessage`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Chat value) {
        stmt.bindLong(1, value.getPrimaryKey());
        if (value.getId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getId());
        }
        final String _tmp = Converters.profileToString(value.getUser());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp);
        }
        final String _tmp_1 = Converters.messageToString(value.getLastMessage());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp_1);
        }
      }
    };
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM chats";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Chat chat) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfChat.insert(chat);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final Chat... chats) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfChat.insert(chats);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clear() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClear.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClear.release(_stmt);
    }
  }

  @Override
  public List<Chat> getAllChats() {
    final String _sql = "SELECT * FROM chats";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPrimaryKey = CursorUtil.getColumnIndexOrThrow(_cursor, "PrimaryKey");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUser = CursorUtil.getColumnIndexOrThrow(_cursor, "user");
      final int _cursorIndexOfLastMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessage");
      final List<Chat> _result = new ArrayList<Chat>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Chat _item;
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final User _tmpUser;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfUser)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfUser);
        }
        _tmpUser = Converters.profileFromString(_tmp);
        _item = new Chat(_tmpId,_tmpUser);
        final int _tmpPrimaryKey;
        _tmpPrimaryKey = _cursor.getInt(_cursorIndexOfPrimaryKey);
        _item.setPrimaryKey(_tmpPrimaryKey);
        final Message _tmpLastMessage;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfLastMessage)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfLastMessage);
        }
        _tmpLastMessage = Converters.messageFromString(_tmp_1);
        _item.setLastMessage(_tmpLastMessage);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Chat getChat(final String chatId) {
    final String _sql = "SELECT * FROM chats WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (chatId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, chatId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPrimaryKey = CursorUtil.getColumnIndexOrThrow(_cursor, "PrimaryKey");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUser = CursorUtil.getColumnIndexOrThrow(_cursor, "user");
      final int _cursorIndexOfLastMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessage");
      final Chat _result;
      if(_cursor.moveToFirst()) {
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final User _tmpUser;
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfUser)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfUser);
        }
        _tmpUser = Converters.profileFromString(_tmp);
        _result = new Chat(_tmpId,_tmpUser);
        final int _tmpPrimaryKey;
        _tmpPrimaryKey = _cursor.getInt(_cursorIndexOfPrimaryKey);
        _result.setPrimaryKey(_tmpPrimaryKey);
        final Message _tmpLastMessage;
        final String _tmp_1;
        if (_cursor.isNull(_cursorIndexOfLastMessage)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getString(_cursorIndexOfLastMessage);
        }
        _tmpLastMessage = Converters.messageFromString(_tmp_1);
        _result.setLastMessage(_tmpLastMessage);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
