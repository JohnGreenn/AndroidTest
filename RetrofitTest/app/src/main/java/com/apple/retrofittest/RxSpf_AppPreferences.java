package com.apple.retrofittest;

import android.content.Context;
import android.content.SharedPreferences;

import me.yokeyword.rxapi.spf.EditorHelper;
import me.yokeyword.rxapi.spf.SpfHelper;
import me.yokeyword.rxapi.spf.field.BooleanEditorField;
import me.yokeyword.rxapi.spf.field.BooleanSpfField;
import me.yokeyword.rxapi.spf.field.IntEditorField;
import me.yokeyword.rxapi.spf.field.IntSpfField;
import me.yokeyword.rxapi.spf.field.LongEditorField;
import me.yokeyword.rxapi.spf.field.LongSpfField;
import me.yokeyword.rxapi.spf.field.StringEditorField;
import me.yokeyword.rxapi.spf.field.StringSpfField;

public final class RxSpf_AppPreferences extends SpfHelper {
    private RxSpf_AppPreferences(Context context) {
        super(context, "_AppPreferences");
    }

    public static RxSpf_AppPreferences create(Context context) {
        return new RxSpf_AppPreferences(context);
    }

    public BooleanSpfField isexit() {
        return new BooleanSpfField(sharedPreferences,"isexit");
    }

    public StringSpfField Uuid() {
        return new StringSpfField(sharedPreferences,"Uuid");
    }

    public BooleanSpfField first() {
        return new BooleanSpfField(sharedPreferences,"first");
    }

    public BooleanSpfField isadclose() {
        return new BooleanSpfField(sharedPreferences,"isadclose");
    }

    public BooleanSpfField isdbadclose() {
        return new BooleanSpfField(sharedPreferences,"isdbadclose");
    }

    public StringSpfField UserId() {
        return new StringSpfField(sharedPreferences,"UserId");
    }

    public StringSpfField Token() {
        return new StringSpfField(sharedPreferences,"Token");
    }

    public BooleanSpfField ispush() {
        return new BooleanSpfField(sharedPreferences,"ispush");
    }

    public StringSpfField registration_id() {
        return new StringSpfField(sharedPreferences,"registration_id");
    }

    public LongSpfField start_time() {
        return new LongSpfField(sharedPreferences,"start_time");
    }

    public StringSpfField sertchHos() {
        return new StringSpfField(sharedPreferences,"sertchHos");
    }

    public StringSpfField sertchLogHos() {
        return new StringSpfField(sharedPreferences,"sertchLogHos");
    }

    public IntSpfField textSize() {
        return new IntSpfField(sharedPreferences,"textSize");
    }

    public IntSpfField loginType() {
        return new IntSpfField(sharedPreferences,"loginType");
    }

    public BooleanSpfField newVersion() {
        return new BooleanSpfField(sharedPreferences,"newVersion");
    }

    public Editor_AppPreferences edit() {
        return new Editor_AppPreferences(getEditor());
    }

    public final class Editor_AppPreferences extends EditorHelper {
        public Editor_AppPreferences(SharedPreferences.Editor editor) {
            super(editor);
        }

        public BooleanEditorField<Editor_AppPreferences> isexit() {
            return new BooleanEditorField(this,"isexit");
        }

        public StringEditorField<Editor_AppPreferences> Uuid() {
            return new StringEditorField(this,"Uuid");
        }

        public BooleanEditorField<Editor_AppPreferences> first() {
            return new BooleanEditorField(this,"first");
        }

        public BooleanEditorField<Editor_AppPreferences> isadclose() {
            return new BooleanEditorField(this,"isadclose");
        }

        public BooleanEditorField<Editor_AppPreferences> isdbadclose() {
            return new BooleanEditorField(this,"isdbadclose");
        }

        public StringEditorField<Editor_AppPreferences> UserId() {
            return new StringEditorField(this,"UserId");
        }

        public StringEditorField<Editor_AppPreferences> Token() {
            return new StringEditorField(this,"Token");
        }

        public BooleanEditorField<Editor_AppPreferences> ispush() {
            return new BooleanEditorField(this,"ispush");
        }

        public StringEditorField<Editor_AppPreferences> registration_id() {
            return new StringEditorField(this,"registration_id");
        }

        public LongEditorField<Editor_AppPreferences> start_time() {
            return new LongEditorField(this,"start_time");
        }

        public StringEditorField<Editor_AppPreferences> sertchHos() {
            return new StringEditorField(this,"sertchHos");
        }

        public StringEditorField<Editor_AppPreferences> sertchLogHos() {
            return new StringEditorField(this,"sertchLogHos");
        }

        public IntEditorField<Editor_AppPreferences> textSize() {
            return new IntEditorField(this,"textSize");
        }

        public IntEditorField<Editor_AppPreferences> loginType() {
            return new IntEditorField(this,"loginType");
        }

        public BooleanEditorField<Editor_AppPreferences> newVersion() {
            return new BooleanEditorField(this,"newVersion");
        }
    }
}