# scala-sequencer

TidalCyclesのScala版のようなものを作ろうという試み。  
ScalaからMIDIを叩くプロトタイプ。  

# メモ

## デバッグに便利なツール

Pocket MIDI : https://www.morson.jp/pocketmidi-webpage/  

## ドキュメント

javax.sound.midi (Java Platform SE 8 ) : https://docs.oracle.com/javase/jp/8/docs/api/javax/sound/midi/package-summary.html  

## MIDIの設定について

仮想MIDIポート経由でメッセージをやり取りする必要がある。  

仮想MIDIバスの設定する – Ableton : https://help.ableton.com/hc/ja/articles/209774225-%E4%BB%AE%E6%83%B3MIDI%E3%83%90%E3%82%B9%E3%81%AE%E8%A8%AD%E5%AE%9A%E3%81%99%E3%82%8B  

# 案

C1 C1 C1 C1 と書いたときに4分音符が4つ並んで  
C1 C1 と書いたときに2分音符が2つ並んで  
C1 と書いたとき全音符が並ぶ  

## ショートハンドについて

例1
```
C1 * 4
```
と
```
C1 C1 C1 C1
```
は同じ

例2
```
(C1 C1) * 2
```
と
```
C1 C1 C1 C1
```
は同じ

というような機能をDSLで提供するのは良いかもしれないけどScalaそのものをできるだけ活かしたいのでノートは個数だけ数えて1小節に収まるように均等にtickを割り降るように実装するのが良いのではないか、と思っている。
