.class Lcom/duokan/reader/ui/general/cy;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field final synthetic a:Ljava/lang/Runnable;

.field final synthetic b:Lcom/duokan/reader/ui/general/cw;


# direct methods
.method constructor <init>(Lcom/duokan/reader/ui/general/cw;Ljava/lang/Runnable;)V
    .locals 0
    .parameter
    .parameter

    .prologue
    .line 544
    iput-object p1, p0, Lcom/duokan/reader/ui/general/cy;->b:Lcom/duokan/reader/ui/general/cw;

    iput-object p2, p0, Lcom/duokan/reader/ui/general/cy;->a:Ljava/lang/Runnable;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    .prologue
    .line 547
    iget-object v0, p0, Lcom/duokan/reader/ui/general/cy;->b:Lcom/duokan/reader/ui/general/cw;

    const/4 v1, -0x1

    invoke-static {v0, v1}, Lcom/duokan/reader/ui/general/cw;->a(Lcom/duokan/reader/ui/general/cw;I)I

    .line 548
    iget-object v0, p0, Lcom/duokan/reader/ui/general/cy;->b:Lcom/duokan/reader/ui/general/cw;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/duokan/reader/ui/general/cw;->b(Lcom/duokan/reader/ui/general/cw;Ljava/lang/Runnable;)Ljava/lang/Runnable;

    .line 550
    iget-object v0, p0, Lcom/duokan/reader/ui/general/cy;->a:Ljava/lang/Runnable;

    if-eqz v0, :cond_0

    .line 551
    iget-object v0, p0, Lcom/duokan/reader/ui/general/cy;->a:Ljava/lang/Runnable;

    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    .line 553
    :cond_0
    return-void
.end method
