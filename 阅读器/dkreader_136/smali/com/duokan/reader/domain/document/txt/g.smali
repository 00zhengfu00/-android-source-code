.class Lcom/duokan/reader/domain/document/txt/g;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/duokan/reader/domain/document/aa;


# instance fields
.field final synthetic a:Lcom/duokan/reader/domain/document/txt/f;


# direct methods
.method constructor <init>(Lcom/duokan/reader/domain/document/txt/f;)V
    .locals 0
    .parameter

    .prologue
    .line 76
    iput-object p1, p0, Lcom/duokan/reader/domain/document/txt/g;->a:Lcom/duokan/reader/domain/document/txt/f;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public a(Lcom/duokan/reader/domain/document/j;Lcom/duokan/reader/domain/document/y;)V
    .locals 3
    .parameter
    .parameter

    .prologue
    .line 79
    iget-object v0, p0, Lcom/duokan/reader/domain/document/txt/g;->a:Lcom/duokan/reader/domain/document/txt/f;

    iget-object v0, v0, Lcom/duokan/reader/domain/document/txt/f;->a:Lcom/duokan/reader/domain/document/txt/d;

    invoke-static {v0}, Lcom/duokan/reader/domain/document/txt/d;->a(Lcom/duokan/reader/domain/document/txt/d;)Lcom/duokan/reader/domain/document/aa;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 80
    iget-object v0, p0, Lcom/duokan/reader/domain/document/txt/g;->a:Lcom/duokan/reader/domain/document/txt/f;

    iget-object v0, v0, Lcom/duokan/reader/domain/document/txt/f;->a:Lcom/duokan/reader/domain/document/txt/d;

    invoke-static {v0}, Lcom/duokan/reader/domain/document/txt/d;->a(Lcom/duokan/reader/domain/document/txt/d;)Lcom/duokan/reader/domain/document/aa;

    move-result-object v0

    const/4 v1, 0x0

    iget-object v2, p0, Lcom/duokan/reader/domain/document/txt/g;->a:Lcom/duokan/reader/domain/document/txt/f;

    iget-object v2, v2, Lcom/duokan/reader/domain/document/txt/f;->a:Lcom/duokan/reader/domain/document/txt/d;

    invoke-interface {v0, v1, v2}, Lcom/duokan/reader/domain/document/aa;->a(Lcom/duokan/reader/domain/document/j;Lcom/duokan/reader/domain/document/y;)V

    .line 82
    :cond_0
    return-void
.end method

.method public b(Lcom/duokan/reader/domain/document/j;Lcom/duokan/reader/domain/document/y;)V
    .locals 3
    .parameter
    .parameter

    .prologue
    .line 85
    iget-object v0, p0, Lcom/duokan/reader/domain/document/txt/g;->a:Lcom/duokan/reader/domain/document/txt/f;

    iget-object v0, v0, Lcom/duokan/reader/domain/document/txt/f;->a:Lcom/duokan/reader/domain/document/txt/d;

    invoke-static {v0}, Lcom/duokan/reader/domain/document/txt/d;->a(Lcom/duokan/reader/domain/document/txt/d;)Lcom/duokan/reader/domain/document/aa;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 86
    iget-object v0, p0, Lcom/duokan/reader/domain/document/txt/g;->a:Lcom/duokan/reader/domain/document/txt/f;

    iget-object v0, v0, Lcom/duokan/reader/domain/document/txt/f;->a:Lcom/duokan/reader/domain/document/txt/d;

    invoke-static {v0}, Lcom/duokan/reader/domain/document/txt/d;->a(Lcom/duokan/reader/domain/document/txt/d;)Lcom/duokan/reader/domain/document/aa;

    move-result-object v0

    const/4 v1, 0x0

    iget-object v2, p0, Lcom/duokan/reader/domain/document/txt/g;->a:Lcom/duokan/reader/domain/document/txt/f;

    iget-object v2, v2, Lcom/duokan/reader/domain/document/txt/f;->a:Lcom/duokan/reader/domain/document/txt/d;

    invoke-interface {v0, v1, v2}, Lcom/duokan/reader/domain/document/aa;->b(Lcom/duokan/reader/domain/document/j;Lcom/duokan/reader/domain/document/y;)V

    .line 88
    :cond_0
    return-void
.end method
