.class Lcom/duokan/reader/domain/document/epub/k;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/os/Handler$Callback;


# instance fields
.field final synthetic a:Lcom/duokan/reader/domain/document/epub/f;


# direct methods
.method constructor <init>(Lcom/duokan/reader/domain/document/epub/f;)V
    .locals 0
    .parameter

    .prologue
    .line 381
    iput-object p1, p0, Lcom/duokan/reader/domain/document/epub/k;->a:Lcom/duokan/reader/domain/document/epub/f;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public handleMessage(Landroid/os/Message;)Z
    .locals 2
    .parameter

    .prologue
    const/4 v1, 0x1

    .line 384
    iget-object v0, p0, Lcom/duokan/reader/domain/document/epub/k;->a:Lcom/duokan/reader/domain/document/epub/f;

    invoke-virtual {v0}, Lcom/duokan/reader/domain/document/epub/f;->invalidateSelf()V

    .line 385
    iget-object v0, p0, Lcom/duokan/reader/domain/document/epub/k;->a:Lcom/duokan/reader/domain/document/epub/f;

    invoke-static {v0, v1}, Lcom/duokan/reader/domain/document/epub/f;->b(Lcom/duokan/reader/domain/document/epub/f;Z)Z

    .line 386
    return v1
.end method
