.class Lcom/duokan/reader/domain/document/epub/p;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field final synthetic a:Lcom/duokan/reader/domain/document/epub/o;


# direct methods
.method constructor <init>(Lcom/duokan/reader/domain/document/epub/o;)V
    .locals 0
    .parameter

    .prologue
    .line 122
    iput-object p1, p0, Lcom/duokan/reader/domain/document/epub/p;->a:Lcom/duokan/reader/domain/document/epub/o;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 1

    .prologue
    .line 125
    iget-object v0, p0, Lcom/duokan/reader/domain/document/epub/p;->a:Lcom/duokan/reader/domain/document/epub/o;

    invoke-static {v0}, Lcom/duokan/reader/domain/document/epub/o;->a(Lcom/duokan/reader/domain/document/epub/o;)V

    .line 126
    return-void
.end method
