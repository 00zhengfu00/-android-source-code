.class public final enum Lcom/duokan/reader/ui/general/expandable/ViewMode;
.super Ljava/lang/Enum;
.source "SourceFile"


# static fields
.field private static final synthetic $VALUES:[Lcom/duokan/reader/ui/general/expandable/ViewMode;

.field public static final enum Edit:Lcom/duokan/reader/ui/general/expandable/ViewMode;

.field public static final enum Normal:Lcom/duokan/reader/ui/general/expandable/ViewMode;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .prologue
    const/4 v3, 0x1

    const/4 v2, 0x0

    .line 4
    new-instance v0, Lcom/duokan/reader/ui/general/expandable/ViewMode;

    const-string v1, "Normal"

    invoke-direct {v0, v1, v2}, Lcom/duokan/reader/ui/general/expandable/ViewMode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/duokan/reader/ui/general/expandable/ViewMode;->Normal:Lcom/duokan/reader/ui/general/expandable/ViewMode;

    .line 5
    new-instance v0, Lcom/duokan/reader/ui/general/expandable/ViewMode;

    const-string v1, "Edit"

    invoke-direct {v0, v1, v3}, Lcom/duokan/reader/ui/general/expandable/ViewMode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/duokan/reader/ui/general/expandable/ViewMode;->Edit:Lcom/duokan/reader/ui/general/expandable/ViewMode;

    .line 3
    const/4 v0, 0x2

    new-array v0, v0, [Lcom/duokan/reader/ui/general/expandable/ViewMode;

    sget-object v1, Lcom/duokan/reader/ui/general/expandable/ViewMode;->Normal:Lcom/duokan/reader/ui/general/expandable/ViewMode;

    aput-object v1, v0, v2

    sget-object v1, Lcom/duokan/reader/ui/general/expandable/ViewMode;->Edit:Lcom/duokan/reader/ui/general/expandable/ViewMode;

    aput-object v1, v0, v3

    sput-object v0, Lcom/duokan/reader/ui/general/expandable/ViewMode;->$VALUES:[Lcom/duokan/reader/ui/general/expandable/ViewMode;

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

.method public static valueOf(Ljava/lang/String;)Lcom/duokan/reader/ui/general/expandable/ViewMode;
    .locals 1
    .parameter

    .prologue
    .line 3
    const-class v0, Lcom/duokan/reader/ui/general/expandable/ViewMode;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object v0

    check-cast v0, Lcom/duokan/reader/ui/general/expandable/ViewMode;

    return-object v0
.end method

.method public static values()[Lcom/duokan/reader/ui/general/expandable/ViewMode;
    .locals 1

    .prologue
    .line 3
    sget-object v0, Lcom/duokan/reader/ui/general/expandable/ViewMode;->$VALUES:[Lcom/duokan/reader/ui/general/expandable/ViewMode;

    invoke-virtual {v0}, [Lcom/duokan/reader/ui/general/expandable/ViewMode;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/duokan/reader/ui/general/expandable/ViewMode;

    return-object v0
.end method
