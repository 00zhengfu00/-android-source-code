.class Lcom/evernote/edam/notestore/NoteStore$untagAll_args;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/evernote/thrift/TBase;
.implements Ljava/io/Serializable;
.implements Ljava/lang/Cloneable;


# static fields
.field private static final AUTHENTICATION_TOKEN_FIELD_DESC:Lcom/evernote/thrift/protocol/TField;

.field private static final GUID_FIELD_DESC:Lcom/evernote/thrift/protocol/TField;

.field private static final STRUCT_DESC:Lcom/evernote/thrift/protocol/TStruct;


# instance fields
.field private authenticationToken:Ljava/lang/String;

.field private guid:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .prologue
    const/16 v3, 0xb

    .line 9136
    new-instance v0, Lcom/evernote/thrift/protocol/TStruct;

    const-string v1, "untagAll_args"

    invoke-direct {v0, v1}, Lcom/evernote/thrift/protocol/TStruct;-><init>(Ljava/lang/String;)V

    sput-object v0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->STRUCT_DESC:Lcom/evernote/thrift/protocol/TStruct;

    .line 9138
    new-instance v0, Lcom/evernote/thrift/protocol/TField;

    const-string v1, "authenticationToken"

    const/4 v2, 0x1

    invoke-direct {v0, v1, v3, v2}, Lcom/evernote/thrift/protocol/TField;-><init>(Ljava/lang/String;BS)V

    sput-object v0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->AUTHENTICATION_TOKEN_FIELD_DESC:Lcom/evernote/thrift/protocol/TField;

    .line 9139
    new-instance v0, Lcom/evernote/thrift/protocol/TField;

    const-string v1, "guid"

    const/4 v2, 0x2

    invoke-direct {v0, v1, v3, v2}, Lcom/evernote/thrift/protocol/TField;-><init>(Ljava/lang/String;BS)V

    sput-object v0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->GUID_FIELD_DESC:Lcom/evernote/thrift/protocol/TField;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 9147
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 9148
    return-void
.end method

.method public constructor <init>(Lcom/evernote/edam/notestore/NoteStore$untagAll_args;)V
    .locals 1
    .parameter

    .prologue
    .line 9153
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 9154
    invoke-virtual {p1}, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->isSetAuthenticationToken()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 9155
    iget-object v0, p1, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->authenticationToken:Ljava/lang/String;

    iput-object v0, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->authenticationToken:Ljava/lang/String;

    .line 9157
    :cond_0
    invoke-virtual {p1}, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->isSetGuid()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 9158
    iget-object v0, p1, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->guid:Ljava/lang/String;

    iput-object v0, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->guid:Ljava/lang/String;

    .line 9160
    :cond_1
    return-void
.end method


# virtual methods
.method public clear()V
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 9167
    iput-object v0, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->authenticationToken:Ljava/lang/String;

    .line 9168
    iput-object v0, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->guid:Ljava/lang/String;

    .line 9169
    return-void
.end method

.method public compareTo(Lcom/evernote/edam/notestore/NoteStore$untagAll_args;)I
    .locals 2
    .parameter

    .prologue
    .line 9190
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 9191
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result v0

    .line 9215
    :cond_0
    :goto_0
    return v0

    .line 9197
    :cond_1
    invoke-virtual {p0}, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->isSetAuthenticationToken()Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-virtual {p1}, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->isSetAuthenticationToken()Z

    move-result v1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/Boolean;->compareTo(Ljava/lang/Boolean;)I

    move-result v0

    .line 9198
    if-nez v0, :cond_0

    .line 9201
    invoke-virtual {p0}, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->isSetAuthenticationToken()Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->authenticationToken:Ljava/lang/String;

    iget-object v1, p1, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->authenticationToken:Ljava/lang/String;

    invoke-static {v0, v1}, Lcom/evernote/thrift/TBaseHelper;->compareTo(Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    .line 9202
    if-nez v0, :cond_0

    .line 9206
    :cond_2
    invoke-virtual {p0}, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->isSetGuid()Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-virtual {p1}, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->isSetGuid()Z

    move-result v1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/Boolean;->compareTo(Ljava/lang/Boolean;)I

    move-result v0

    .line 9207
    if-nez v0, :cond_0

    .line 9210
    invoke-virtual {p0}, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->isSetGuid()Z

    move-result v0

    if-eqz v0, :cond_3

    iget-object v0, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->guid:Ljava/lang/String;

    iget-object v1, p1, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->guid:Ljava/lang/String;

    invoke-static {v0, v1}, Lcom/evernote/thrift/TBaseHelper;->compareTo(Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    .line 9211
    if-nez v0, :cond_0

    .line 9215
    :cond_3
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public bridge synthetic compareTo(Ljava/lang/Object;)I
    .locals 1
    .parameter

    .prologue
    .line 9135
    check-cast p1, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;

    invoke-virtual {p0, p1}, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->compareTo(Lcom/evernote/edam/notestore/NoteStore$untagAll_args;)I

    move-result v0

    return v0
.end method

.method public deepCopy()Lcom/evernote/edam/notestore/NoteStore$untagAll_args;
    .locals 1

    .prologue
    .line 9163
    new-instance v0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;

    invoke-direct {v0, p0}, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;-><init>(Lcom/evernote/edam/notestore/NoteStore$untagAll_args;)V

    return-object v0
.end method

.method public bridge synthetic deepCopy()Lcom/evernote/thrift/TBase;
    .locals 1

    .prologue
    .line 9135
    invoke-virtual {p0}, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->deepCopy()Lcom/evernote/edam/notestore/NoteStore$untagAll_args;

    move-result-object v0

    return-object v0
.end method

.method public isSetAuthenticationToken()Z
    .locals 1

    .prologue
    .line 9177
    iget-object v0, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->authenticationToken:Ljava/lang/String;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isSetGuid()Z
    .locals 1

    .prologue
    .line 9186
    iget-object v0, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->guid:Ljava/lang/String;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public read(Lcom/evernote/thrift/protocol/TProtocol;)V
    .locals 3
    .parameter

    .prologue
    const/16 v2, 0xb

    .line 9220
    invoke-virtual {p1}, Lcom/evernote/thrift/protocol/TProtocol;->readStructBegin()Lcom/evernote/thrift/protocol/TStruct;

    .line 9223
    :goto_0
    invoke-virtual {p1}, Lcom/evernote/thrift/protocol/TProtocol;->readFieldBegin()Lcom/evernote/thrift/protocol/TField;

    move-result-object v0

    .line 9224
    iget-byte v1, v0, Lcom/evernote/thrift/protocol/TField;->type:B

    if-nez v1, :cond_0

    .line 9247
    invoke-virtual {p1}, Lcom/evernote/thrift/protocol/TProtocol;->readStructEnd()V

    .line 9248
    invoke-virtual {p0}, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->validate()V

    .line 9249
    return-void

    .line 9227
    :cond_0
    iget-short v1, v0, Lcom/evernote/thrift/protocol/TField;->id:S

    packed-switch v1, :pswitch_data_0

    .line 9243
    iget-byte v0, v0, Lcom/evernote/thrift/protocol/TField;->type:B

    invoke-static {p1, v0}, Lcom/evernote/thrift/protocol/TProtocolUtil;->skip(Lcom/evernote/thrift/protocol/TProtocol;B)V

    .line 9245
    :goto_1
    invoke-virtual {p1}, Lcom/evernote/thrift/protocol/TProtocol;->readFieldEnd()V

    goto :goto_0

    .line 9229
    :pswitch_0
    iget-byte v1, v0, Lcom/evernote/thrift/protocol/TField;->type:B

    if-ne v1, v2, :cond_1

    .line 9230
    invoke-virtual {p1}, Lcom/evernote/thrift/protocol/TProtocol;->readString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->authenticationToken:Ljava/lang/String;

    goto :goto_1

    .line 9232
    :cond_1
    iget-byte v0, v0, Lcom/evernote/thrift/protocol/TField;->type:B

    invoke-static {p1, v0}, Lcom/evernote/thrift/protocol/TProtocolUtil;->skip(Lcom/evernote/thrift/protocol/TProtocol;B)V

    goto :goto_1

    .line 9236
    :pswitch_1
    iget-byte v1, v0, Lcom/evernote/thrift/protocol/TField;->type:B

    if-ne v1, v2, :cond_2

    .line 9237
    invoke-virtual {p1}, Lcom/evernote/thrift/protocol/TProtocol;->readString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->guid:Ljava/lang/String;

    goto :goto_1

    .line 9239
    :cond_2
    iget-byte v0, v0, Lcom/evernote/thrift/protocol/TField;->type:B

    invoke-static {p1, v0}, Lcom/evernote/thrift/protocol/TProtocolUtil;->skip(Lcom/evernote/thrift/protocol/TProtocol;B)V

    goto :goto_1

    .line 9227
    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method public setAuthenticationToken(Ljava/lang/String;)V
    .locals 0
    .parameter

    .prologue
    .line 9172
    iput-object p1, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->authenticationToken:Ljava/lang/String;

    .line 9173
    return-void
.end method

.method public setGuid(Ljava/lang/String;)V
    .locals 0
    .parameter

    .prologue
    .line 9181
    iput-object p1, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->guid:Ljava/lang/String;

    .line 9182
    return-void
.end method

.method public validate()V
    .locals 0

    .prologue
    .line 9271
    return-void
.end method

.method public write(Lcom/evernote/thrift/protocol/TProtocol;)V
    .locals 1
    .parameter

    .prologue
    .line 9252
    invoke-virtual {p0}, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->validate()V

    .line 9254
    sget-object v0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->STRUCT_DESC:Lcom/evernote/thrift/protocol/TStruct;

    invoke-virtual {p1, v0}, Lcom/evernote/thrift/protocol/TProtocol;->writeStructBegin(Lcom/evernote/thrift/protocol/TStruct;)V

    .line 9255
    iget-object v0, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->authenticationToken:Ljava/lang/String;

    if-eqz v0, :cond_0

    .line 9256
    sget-object v0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->AUTHENTICATION_TOKEN_FIELD_DESC:Lcom/evernote/thrift/protocol/TField;

    invoke-virtual {p1, v0}, Lcom/evernote/thrift/protocol/TProtocol;->writeFieldBegin(Lcom/evernote/thrift/protocol/TField;)V

    .line 9257
    iget-object v0, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->authenticationToken:Ljava/lang/String;

    invoke-virtual {p1, v0}, Lcom/evernote/thrift/protocol/TProtocol;->writeString(Ljava/lang/String;)V

    .line 9258
    invoke-virtual {p1}, Lcom/evernote/thrift/protocol/TProtocol;->writeFieldEnd()V

    .line 9260
    :cond_0
    iget-object v0, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->guid:Ljava/lang/String;

    if-eqz v0, :cond_1

    .line 9261
    sget-object v0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->GUID_FIELD_DESC:Lcom/evernote/thrift/protocol/TField;

    invoke-virtual {p1, v0}, Lcom/evernote/thrift/protocol/TProtocol;->writeFieldBegin(Lcom/evernote/thrift/protocol/TField;)V

    .line 9262
    iget-object v0, p0, Lcom/evernote/edam/notestore/NoteStore$untagAll_args;->guid:Ljava/lang/String;

    invoke-virtual {p1, v0}, Lcom/evernote/thrift/protocol/TProtocol;->writeString(Ljava/lang/String;)V

    .line 9263
    invoke-virtual {p1}, Lcom/evernote/thrift/protocol/TProtocol;->writeFieldEnd()V

    .line 9265
    :cond_1
    invoke-virtual {p1}, Lcom/evernote/thrift/protocol/TProtocol;->writeFieldStop()V

    .line 9266
    invoke-virtual {p1}, Lcom/evernote/thrift/protocol/TProtocol;->writeStructEnd()V

    .line 9267
    return-void
.end method
