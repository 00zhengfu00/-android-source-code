.class Lcom/duokan/reader/domain/document/txt/s;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Handler$Callback;


# static fields
.field static final synthetic a:Z


# instance fields
.field final synthetic b:Lcom/duokan/reader/domain/document/txt/m;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 1130
    const-class v0, Lcom/duokan/reader/domain/document/txt/m;

    invoke-virtual {v0}, Ljava/lang/Class;->desiredAssertionStatus()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    sput-boolean v0, Lcom/duokan/reader/domain/document/txt/s;->a:Z

    return-void

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method constructor <init>(Lcom/duokan/reader/domain/document/txt/m;)V
    .locals 0
    .parameter

    .prologue
    .line 1130
    iput-object p1, p0, Lcom/duokan/reader/domain/document/txt/s;->b:Lcom/duokan/reader/domain/document/txt/m;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public handleMessage(Landroid/os/Message;)Z
    .locals 3
    .parameter

    .prologue
    .line 1133
    iget-object v0, p0, Lcom/duokan/reader/domain/document/txt/s;->b:Lcom/duokan/reader/domain/document/txt/m;

    invoke-static {v0}, Lcom/duokan/reader/domain/document/txt/m;->d(Lcom/duokan/reader/domain/document/txt/m;)Ljava/util/LinkedList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/LinkedList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/duokan/reader/domain/document/k;

    .line 1134
    sget-boolean v2, Lcom/duokan/reader/domain/document/txt/s;->a:Z

    if-nez v2, :cond_0

    if-nez v0, :cond_0

    new-instance v0, Ljava/lang/AssertionError;

    invoke-direct {v0}, Ljava/lang/AssertionError;-><init>()V

    throw v0

    .line 1135
    :cond_0
    iget-object v2, p0, Lcom/duokan/reader/domain/document/txt/s;->b:Lcom/duokan/reader/domain/document/txt/m;

    invoke-interface {v0, v2}, Lcom/duokan/reader/domain/document/k;->a(Lcom/duokan/reader/domain/document/j;)V

    goto :goto_0

    .line 1137
    :cond_1
    const/4 v0, 0x1

    return v0
.end method
