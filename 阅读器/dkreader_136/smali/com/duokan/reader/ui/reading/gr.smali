.class Lcom/duokan/reader/ui/reading/gr;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroid/view/View$OnClickListener;


# instance fields
.field final synthetic a:Lcom/duokan/reader/ui/reading/go;


# direct methods
.method constructor <init>(Lcom/duokan/reader/ui/reading/go;)V
    .locals 0
    .parameter

    .prologue
    .line 256
    iput-object p1, p0, Lcom/duokan/reader/ui/reading/gr;->a:Lcom/duokan/reader/ui/reading/go;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 3
    .parameter

    .prologue
    .line 260
    invoke-static {}, Lcom/duokan/reader/UmengManager;->get()Lcom/duokan/reader/UmengManager;

    move-result-object v0

    const-string v1, "V2_READING_OPTION_TYPOGRAPHY"

    const-string v2, "3"

    invoke-virtual {v0, v1, v2}, Lcom/duokan/reader/UmengManager;->onEvent(Ljava/lang/String;Ljava/lang/String;)V

    .line 262
    iget-object v0, p0, Lcom/duokan/reader/ui/reading/gr;->a:Lcom/duokan/reader/ui/reading/go;

    invoke-static {v0}, Lcom/duokan/reader/ui/reading/go;->c(Lcom/duokan/reader/ui/reading/go;)Lcom/duokan/reader/ui/reading/eb;

    move-result-object v0

    sget-object v1, Lcom/duokan/reader/ui/reading/ReadingMode;->NORMAL:Lcom/duokan/reader/ui/reading/ReadingMode;

    invoke-interface {v0, v1}, Lcom/duokan/reader/ui/reading/eb;->a(Lcom/duokan/reader/ui/reading/ReadingMode;)V

    .line 263
    iget-object v0, p0, Lcom/duokan/reader/ui/reading/gr;->a:Lcom/duokan/reader/ui/reading/go;

    invoke-static {v0}, Lcom/duokan/reader/ui/reading/go;->c(Lcom/duokan/reader/ui/reading/go;)Lcom/duokan/reader/ui/reading/eb;

    move-result-object v0

    invoke-interface {v0}, Lcom/duokan/reader/ui/reading/eb;->z()Lcom/duokan/reader/ui/reading/ReadingPrefs;

    move-result-object v0

    sget-object v1, Lcom/duokan/reader/ui/reading/TypesettingStyle;->LOOSE:Lcom/duokan/reader/ui/reading/TypesettingStyle;

    invoke-virtual {v0, v1}, Lcom/duokan/reader/ui/reading/ReadingPrefs;->a(Lcom/duokan/reader/ui/reading/TypesettingStyle;)V

    .line 264
    iget-object v0, p0, Lcom/duokan/reader/ui/reading/gr;->a:Lcom/duokan/reader/ui/reading/go;

    invoke-static {v0}, Lcom/duokan/reader/ui/reading/go;->c(Lcom/duokan/reader/ui/reading/go;)Lcom/duokan/reader/ui/reading/eb;

    move-result-object v0

    invoke-interface {v0}, Lcom/duokan/reader/ui/reading/eb;->z()Lcom/duokan/reader/ui/reading/ReadingPrefs;

    move-result-object v0

    invoke-virtual {v0}, Lcom/duokan/reader/ui/reading/ReadingPrefs;->J()V

    .line 265
    iget-object v0, p0, Lcom/duokan/reader/ui/reading/gr;->a:Lcom/duokan/reader/ui/reading/go;

    invoke-static {v0}, Lcom/duokan/reader/ui/reading/go;->c(Lcom/duokan/reader/ui/reading/go;)Lcom/duokan/reader/ui/reading/eb;

    move-result-object v0

    invoke-interface {v0}, Lcom/duokan/reader/ui/reading/eb;->X()V

    .line 266
    iget-object v0, p0, Lcom/duokan/reader/ui/reading/gr;->a:Lcom/duokan/reader/ui/reading/go;

    invoke-static {v0}, Lcom/duokan/reader/ui/reading/go;->d(Lcom/duokan/reader/ui/reading/go;)V

    .line 267
    return-void
.end method
