.class public final enum Lcom/duokan/reader/domain/downloadcenter/DownloadType;
.super Ljava/lang/Enum;
.source "SourceFile"


# static fields
.field private static final synthetic $VALUES:[Lcom/duokan/reader/domain/downloadcenter/DownloadType;

.field public static final enum BOOK:Lcom/duokan/reader/domain/downloadcenter/DownloadType;

.field public static final enum FONT:Lcom/duokan/reader/domain/downloadcenter/DownloadType;

.field public static final enum PLUGIN:Lcom/duokan/reader/domain/downloadcenter/DownloadType;


# direct methods
.method static constructor <clinit>()V
    .locals 5

    .prologue
    const/4 v4, 0x2

    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 4
    new-instance v0, Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    const-string v1, "BOOK"

    invoke-direct {v0, v1, v2}, Lcom/duokan/reader/domain/downloadcenter/DownloadType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/duokan/reader/domain/downloadcenter/DownloadType;->BOOK:Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    .line 5
    new-instance v0, Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    const-string v1, "FONT"

    invoke-direct {v0, v1, v3}, Lcom/duokan/reader/domain/downloadcenter/DownloadType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/duokan/reader/domain/downloadcenter/DownloadType;->FONT:Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    .line 6
    new-instance v0, Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    const-string v1, "PLUGIN"

    invoke-direct {v0, v1, v4}, Lcom/duokan/reader/domain/downloadcenter/DownloadType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/duokan/reader/domain/downloadcenter/DownloadType;->PLUGIN:Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    .line 3
    const/4 v0, 0x3

    new-array v0, v0, [Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    sget-object v1, Lcom/duokan/reader/domain/downloadcenter/DownloadType;->BOOK:Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    aput-object v1, v0, v2

    sget-object v1, Lcom/duokan/reader/domain/downloadcenter/DownloadType;->FONT:Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    aput-object v1, v0, v3

    sget-object v1, Lcom/duokan/reader/domain/downloadcenter/DownloadType;->PLUGIN:Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    aput-object v1, v0, v4

    sput-object v0, Lcom/duokan/reader/domain/downloadcenter/DownloadType;->$VALUES:[Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .parameter
    .parameter

    .prologue
    .line 3
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/duokan/reader/domain/downloadcenter/DownloadType;
    .locals 1
    .parameter

    .prologue
    .line 3
    const-class v0, Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object v0

    check-cast v0, Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    return-object v0
.end method

.method public static values()[Lcom/duokan/reader/domain/downloadcenter/DownloadType;
    .locals 1

    .prologue
    .line 3
    sget-object v0, Lcom/duokan/reader/domain/downloadcenter/DownloadType;->$VALUES:[Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    invoke-virtual {v0}, [Lcom/duokan/reader/domain/downloadcenter/DownloadType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/duokan/reader/domain/downloadcenter/DownloadType;

    return-object v0
.end method
